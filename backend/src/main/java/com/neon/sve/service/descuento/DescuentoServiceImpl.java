package com.neon.sve.service.descuento;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.descuento.DatosActualizarDescuento;
import com.neon.sve.dto.descuento.DatosListadoDescuento;
import com.neon.sve.dto.descuento.DatosRegistroDescuento;
import com.neon.sve.dto.descuento.DatosRespuestaDescuento;
import com.neon.sve.model.producto.Categoria;
import com.neon.sve.model.ventas.Descuento;
import com.neon.sve.repository.CategoriaRepository;
import com.neon.sve.repository.DescuentoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DescuentoServiceImpl implements DescuentoService {

    @Autowired
    private DescuentoRepository descuentoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public DatosRespuestaDescuento getDescuentoById(Long id) {
        Descuento descuento = descuentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Descuento no encontrado con ID: " + id));
        return new DatosRespuestaDescuento(descuento);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DatosListadoDescuento> getAllDescuento(Pageable pageable) {
        return descuentoRepository.findAll(pageable).map(DatosListadoDescuento::new);
    }

    @Transactional
    @Override
    public DatosRespuestaDescuento createDescuento(DatosRegistroDescuento datosRegistroDescuento) {
        // 1. Validaciones iniciales
        Categoria categoria = categoriaRepository.findById(datosRegistroDescuento.id_categoria())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoría no encontrada con ID: " + datosRegistroDescuento.id_categoria()));

        if (Boolean.FALSE.equals(categoria.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede aplicar un descuento a una categoría inactiva.");
        }

        if (datosRegistroDescuento.fecha_fin().isBefore(datosRegistroDescuento.fecha_inicio())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

        // 2. Crear la nueva instancia del descuento
        Descuento nuevoDescuento = new Descuento(datosRegistroDescuento, categoria);

        // 3. Guardar primero para obtener un ID
        // Esto simplifica la lógica de activación, ya que siempre trabajaremos con un
        // objeto persistido.
        Descuento descuentoGuardado = descuentoRepository.save(nuevoDescuento);

        // 4. Gestionar la lógica de activación con el descuento ya guardado
        gestionarLogicaDeActivacion(descuentoGuardado);

        // 5. Guardar los cambios de estado y devolver
        descuentoRepository.save(descuentoGuardado);
        return new DatosRespuestaDescuento(descuentoGuardado);
    }

    @Transactional
    @Override
    public DatosRespuestaDescuento updateDescuento(DatosActualizarDescuento datosActualizarDescuento) {
        Descuento descuentoAActualizar = descuentoRepository.findById(datosActualizarDescuento.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Descuento no encontrado con ID: " + datosActualizarDescuento.id()));

        Categoria categoria = descuentoAActualizar.getCategoria();
        if (datosActualizarDescuento.id_categoria() != null
                && !datosActualizarDescuento.id_categoria().equals(categoria.getId())) {
            categoria = categoriaRepository.findById(datosActualizarDescuento.id_categoria())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Nueva categoría no encontrada con ID: " + datosActualizarDescuento.id_categoria()));
        }

        if (Boolean.FALSE.equals(categoria.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede asociar el descuento a una categoría inactiva.");
        }

        descuentoAActualizar.actualizar(datosActualizarDescuento, categoria);

        if (descuentoAActualizar.getFechaFin().isBefore(descuentoAActualizar.getFecha_inicio())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

        gestionarLogicaDeActivacion(descuentoAActualizar);

        Descuento descuentoActualizado = descuentoRepository.save(descuentoAActualizar);
        return new DatosRespuestaDescuento(descuentoActualizado);
    }

    @Transactional
    @Override
    public void activarDescuento(Long id) {
        Descuento descuento = descuentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Descuento no encontrado con ID: " + id));

        if (descuento.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El descuento ya está activo.");
        }

        // La lógica centralizada se encarga de todo
        gestionarLogicaDeActivacion(descuento);

        // Guardamos el estado modificado por la lógica de activación
        descuentoRepository.save(descuento);

        // Si después de la lógica, el descuento sigue inactivo, es porque hay uno
        // mejor.
        if (!descuento.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede activar: Ya existe un descuento activo con un porcentaje mayor para esta categoría.");
        }
    }

    @Transactional
    @Override
    public void desactivarDescuento(Long id) {
        Descuento descuento = descuentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Descuento no encontrado con el ID ingresado: " + id));

        if (!descuento.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El descuento ya está desactivado.");
        }

        descuento.setActivo(false);
        descuentoRepository.save(descuento);
    }

    /**
     * LÓGICA DE ACTIVACIÓN REFACTORIZADA
     * Centraliza la lógica para determinar si un descuento debe estar activo.
     * Considera la fecha y otros descuentos en la misma categoría.
     * Modifica el estado 'activo' del objeto Descuento y desactiva otros si es
     * necesario.
     */
    private void gestionarLogicaDeActivacion(Descuento descuentoAProcesar) {
        Categoria categoria = descuentoAProcesar.getCategoria();
        LocalDate hoy = LocalDate.now();

        // 1. Validar si el descuento puede ser candidato a estar activo por fecha y
        // categoría.
        boolean esCandidato = !hoy.isBefore(descuentoAProcesar.getFecha_inicio())
                && !hoy.isAfter(descuentoAProcesar.getFechaFin())
                && categoria.getActivo();

        if (!esCandidato) {
            descuentoAProcesar.setActivo(false);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El descuento no puede ser activado porque está fuera del rango de fechas o la categoría está inactiva.");
        }

        // 2. Obtener TODOS los otros descuentos activos para la misma categoría.
        // Se excluye el descuento que estamos procesando para evitar compararlo consigo
        // mismo.
        List<Descuento> otrosDescuentosActivos = descuentoRepository.findByCategoriaAndActivoTrue(categoria)
                .stream()
                .filter(d -> !Objects.equals(d.getId(), descuentoAProcesar.getId()))
                .collect(Collectors.toList());

        // 3. Buscar si existe un descuento mejor (con mayor porcentaje) entre los
        // otros.
        Optional<Descuento> mejorDescuentoExistente = otrosDescuentosActivos.stream()
                .max((d1, d2) -> Double.compare(d1.getPorcentaje(), d2.getPorcentaje()));

        boolean hayUnoMejor = mejorDescuentoExistente.isPresent() &&
                mejorDescuentoExistente.get().getPorcentaje() >= descuentoAProcesar.getPorcentaje();

        // 4. Tomar decisiones basadas en la comparación.
        if (hayUnoMejor) {
            // Si ya existe un descuento mejor o igual, el que procesamos debe estar
            // inactivo.
            descuentoAProcesar.setActivo(false);
        } else {
            // Si el descuento que procesamos es el mejor, se activa.
            descuentoAProcesar.setActivo(true);
            // Y todos los demás descuentos que estaban activos para esa categoría se
            // desactivan.
            for (Descuento otro : otrosDescuentosActivos) {
                otro.setActivo(false);
                descuentoRepository.save(otro);
            }
        }
    }
}
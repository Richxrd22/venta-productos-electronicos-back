package com.neon.sve.service.descuento;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public DatosRespuestaDescuento getDescuentoById(Long id) {
        Optional<Descuento> descuentoOptional = descuentoRepository.findById(id);

        if (descuentoOptional.isPresent()) {
            Descuento descuento = descuentoOptional.get();
            return new DatosRespuestaDescuento(descuento);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Descuento no encontrado");
        }

    }

    @Override
    public Page<DatosListadoDescuento> getAllDescuento(Pageable pageable) {
        Page<Descuento> descuentoPage = descuentoRepository.findAll(pageable);
        return descuentoPage.map(DatosListadoDescuento::new);
    }

    @Transactional
    @Override
    public DatosRespuestaDescuento createDescuento(DatosRegistroDescuento datosRegistroDescuento) {
        // 1. Validaciones iniciales
        Categoria categoria = categoriaRepository.findById(datosRegistroDescuento.id_categoria())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoria no encontrada con ID: " + datosRegistroDescuento.id_categoria()));

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

        // 3. Gestionar la lógica de activación
        gestionarLogicaDeActivacion(nuevoDescuento);

        // 4. Guardar y devolver el resultado
        Descuento descuentoGuardado = descuentoRepository.save(nuevoDescuento);
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

        // Actualizar los datos del descuento
        descuentoAActualizar.actualizar(datosActualizarDescuento, categoria);

        // Validar coherencia de fechas después de la actualización
        if (descuentoAActualizar.getFechaFin().isBefore(descuentoAActualizar.getFecha_inicio())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

        // Re-evaluar el estado activo del descuento con la nueva información
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

        // Usar la lógica centralizada para determinar si puede activarse
        gestionarLogicaDeActivacion(descuento);

        // Si después de la lógica, el descuento sigue inactivo, es porque hay uno
        // mejor.
        if (!descuento.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede activar: Ya existe un descuento activo con un porcentaje igual o mayor para esta categoría.");
        }

        descuentoRepository.save(descuento);
    }

    @Override
    public void desactivarDescuento(Long id) {
        Descuento descuento = descuentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Descuento no encontrado con el ID ingresado : " + id));

        if (!descuento.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El descuento ya está desactivado");

        }

        descuento.setActivo(false);
        descuentoRepository.save(descuento);
    }

    /**
     * Centraliza la lógica para determinar si un descuento debe estar activo.
     * Considera la fecha y otros descuentos en la misma categoría.
     * Modifica el estado 'activo' del objeto Descuento pasado como parámetro y
     * desactiva otros si es necesario.
     */
    private void gestionarLogicaDeActivacion(Descuento descuentoAProcesar) {
        Categoria categoria = descuentoAProcesar.getCategoria();
        LocalDate hoy = LocalDate.now();

        // 1. Validar que el descuento esté dentro de su rango de fechas y que su
        // categoría esté activa.
        boolean puedeSerActivoPorFechaYCategoria = !hoy.isBefore(descuentoAProcesar.getFecha_inicio())
                && !hoy.isAfter(descuentoAProcesar.getFechaFin())
                && categoria.getActivo();

        if (!puedeSerActivoPorFechaYCategoria) {
            descuentoAProcesar.setActivo(false);
            return; // No puede ser activo, no hay nada más que hacer.
        }

        double porcentajeActual = descuentoAProcesar.getPorcentaje();

        // 2. Buscar otros descuentos activos en la MISMA categoría.
        List<Descuento> otrosDescuentosActivos = descuentoRepository
                .findByCategoriaAndActivoTrue(categoria);

        boolean hayUnoMejor = false;
        for (Descuento otro : otrosDescuentosActivos) {
            if (otro.getId().equals(descuentoAProcesar.getId())) {
                continue; // No comparar consigo mismo
            }

            if (otro.getPorcentaje() >= porcentajeActual) {
                hayUnoMejor = true;
                break; // Se encontró uno mejor o igual, el actual no puede ser activo.
            } else {
                // El actual es mejor, se desactiva el otro
                otro.setActivo(false);
                descuentoRepository.save(otro);
            }
        }

        // 3. Establecer el estado final del descuento a procesar.
        if (hayUnoMejor) {
            descuentoAProcesar.setActivo(false);
        } else {
            descuentoAProcesar.setActivo(true);
        }
    }
}
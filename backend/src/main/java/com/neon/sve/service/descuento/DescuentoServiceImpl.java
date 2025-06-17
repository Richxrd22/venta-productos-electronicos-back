package com.neon.sve.service.descuento;

import java.time.LocalDate;
import java.util.ArrayList;
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
        // 1. Validaciones iniciales (las que ya tenías)
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

        // 2. Procesar y guardar el descuento principal
        Descuento descuentoPrincipal = procesarYGuardarDescuentoParaCategoria(datosRegistroDescuento, categoria);

        // 3. Propagar el descuento a todas las subcategorías de forma recursiva
        List<Categoria> todasLasSubcategorias = obtenerTodasLasSubcategorias(categoria);
        for (Categoria subcategoria : todasLasSubcategorias) {
            procesarYGuardarDescuentoParaCategoria(datosRegistroDescuento, subcategoria);
        }

        return new DatosRespuestaDescuento(descuentoPrincipal);
    }

    /**
     * Método privado que centraliza la lógica de validación y creación de un
     * descuento para una categoría específica.
     * Comprueba si existe un descuento activo y decide cuál debe permanecer activo.
     */
    private Descuento procesarYGuardarDescuentoParaCategoria(DatosRegistroDescuento datos, Categoria categoria) {
        // Busca descuentos activos y vigentes para la misma categoría
        List<Descuento> descuentosActivos = descuentoRepository.findByActivoTrueAndCategoriaAndFechaFinAfter(categoria,
                LocalDate.now().minusDays(1));

        boolean elNuevoDebeSerActivo = true;

        for (Descuento existente : descuentosActivos) {
            // Si el nuevo descuento tiene un porcentaje mayor, desactiva el existente.
            if (datos.porcentaje() > existente.getPorcentaje()) {
                existente.setActivo(false);
                descuentoRepository.save(existente);
            } else {
                // Si hay un descuento existente mayor o igual, el nuevo se creará como
                // inactivo.
                elNuevoDebeSerActivo = false;
            }
        }

        Descuento nuevoDescuento = new Descuento(datos, categoria);

        // El descuento solo puede estar activo si está dentro del rango de fechas Y si
        // ganó la competencia de porcentajes.
        LocalDate hoy = LocalDate.now();
        boolean estaEnRangoDeFechas = !hoy.isBefore(nuevoDescuento.getFecha_inicio())
                && !hoy.isAfter(nuevoDescuento.getFechaFin());

        nuevoDescuento.setActivo(estaEnRangoDeFechas && elNuevoDebeSerActivo);

        return descuentoRepository.save(nuevoDescuento);
    }

    /**
     * Obtiene de forma recursiva todas las subcategorías (hijas, nietas, etc.) de
     * una categoría padre.
     * Requiere que la entidad Categoria tenga una lista de sus hijas.
     */
    private List<Categoria> obtenerTodasLasSubcategorias(Categoria categoriaPadre) {
        List<Categoria> listaCompleta = new ArrayList<>();
        // Asumiendo que Categoria tiene un método getSubcategorias() que devuelve sus
        // hijas directas.
        // Es importante que la carga de esta relación no sea LAZY o manejarla
        // adecuadamente.
        List<Categoria> hijasDirectas = categoriaPadre.getSubcategorias();

        if (hijasDirectas != null && !hijasDirectas.isEmpty()) {
            for (Categoria hija : hijasDirectas) {
                listaCompleta.add(hija);
                listaCompleta.addAll(obtenerTodasLasSubcategorias(hija)); // Llamada recursiva
            }
        }
        return listaCompleta;
    }

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

        LocalDate fechaInicio = Optional.ofNullable(datosActualizarDescuento.fecha_inicio())
                .orElse(descuentoAActualizar.getFecha_inicio());
        LocalDate fechaFin = Optional.ofNullable(datosActualizarDescuento.fecha_fin())
                .orElse(descuentoAActualizar.getFechaFin());

        if (fechaFin.isBefore(fechaInicio)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

        descuentoAActualizar.actualizar(datosActualizarDescuento, categoria);

        LocalDate hoy = LocalDate.now();
        boolean estaEnRangoDeFechas = !hoy.isBefore(descuentoAActualizar.getFecha_inicio())
                && !hoy.isAfter(descuentoAActualizar.getFechaFin());

        List<Descuento> descuentosActivos = descuentoRepository
                .findByActivoTrueAndCategoriaAndFechaFinAfter(categoria, LocalDate.now().minusDays(1));

        boolean debeSerActivo = true;

        for (Descuento d : descuentosActivos) {
            if (!d.getId().equals(descuentoAActualizar.getId())) {
                if (descuentoAActualizar.getPorcentaje() > d.getPorcentaje()) {
                    d.setActivo(false);
                    descuentoRepository.save(d);
                } else {
                    debeSerActivo = false;
                }
            }
        }

        descuentoAActualizar.setActivo(estaEnRangoDeFechas && debeSerActivo);
        Descuento descuentoActualizado = descuentoRepository.save(descuentoAActualizar);
        return new DatosRespuestaDescuento(descuentoActualizado);
    }

    @Override
    public void activarDescuento(Long id) {
        Descuento descuento = descuentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Descuento no encontrado con ID: " + id));

        if (descuento.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El descuento ya está activo.");
        }

        if (!descuento.getCategoria().getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede activar el descuento porque su categoría está inactiva.");
        }

        LocalDate hoy = LocalDate.now();
        if (hoy.isBefore(descuento.getFecha_inicio()) || hoy.isAfter(descuento.getFechaFin())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede activar el descuento fuera de su rango de fechas válido.");
        }

        List<Descuento> descuentosActivos = descuentoRepository
                .findByActivoTrueAndCategoriaAndFechaFinAfter(descuento.getCategoria(), LocalDate.now().minusDays(1));

        for (Descuento d : descuentosActivos) {
            if (!d.getId().equals(descuento.getId())
                    && d.getPorcentaje() >= descuento.getPorcentaje()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Ya existe un descuento activo con igual o mayor porcentaje.");
            }
        }

        // Desactivar los demás si este es mayor
        for (Descuento d : descuentosActivos) {
            if (!d.getId().equals(descuento.getId()) && d.getPorcentaje() < descuento.getPorcentaje()) {
                d.setActivo(false);
                descuentoRepository.save(d);
            }
        }

        descuento.setActivo(true);
        descuentoRepository.save(descuento);
    }

    @Override
    public void desactivarDescuento(Long id) {
        Descuento descuento = descuentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Descuento no encontrado con el ID ingresado : " + id));

        if (!descuento.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El descuento ya está desactivada");

        }

        descuento.setActivo(false);
        descuentoRepository.save(descuento);
    }

}

package com.neon.sve.service.descuento;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

    @Override
    public DatosRespuestaDescuento createDescuento(DatosRegistroDescuento datosRegistroDescuento) {

        Categoria categoria = categoriaRepository.findById(datosRegistroDescuento.id_categoria())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoria no encontrada con ID ingresado : " + datosRegistroDescuento.id_categoria()));

        if (Boolean.FALSE.equals(categoria.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede aplicar un descuento a una categoría inactiva.");
        }

        // Fechas del descuento
        LocalDate fechaInicio = datosRegistroDescuento.fecha_inicio();
        LocalDate fechaFin = datosRegistroDescuento.fecha_fin();
        LocalDate hoy = LocalDate.now();

        if (fechaFin.isBefore(fechaInicio)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de fin no puede ser anterior a la fecha de inicio del descuento.");
        }

        Descuento descuento = new Descuento(datosRegistroDescuento, categoria);

        // Si la fecha de inicio es futura, el descuento debe empezar como inactivo
        // o activarse automáticamente al llegar la fecha.
        if (descuento.getFecha_inicio().isAfter(hoy)) {
            descuento.setActivo(false); // Se activará cuando llegue la fecha_inicio
        } else {
            descuento.setActivo(true); // Si la fecha de inicio es hoy o pasada, está activo inmediatamente
        }

        Descuento nuevoDescuento = descuentoRepository.save(descuento);
        return new DatosRespuestaDescuento(nuevoDescuento);

    }

    @Override
    public DatosRespuestaDescuento updateDescuento(DatosActualizarDescuento datosActualizarDescuento) {
        Descuento descuentoAActualizar = descuentoRepository.findById(datosActualizarDescuento.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Descuento no encontrado con ID: " + datosActualizarDescuento.id()));

        // Manejar la actualización de la categoría
        Categoria categoria = descuentoAActualizar.getId_categoria(); // Categoría actual del descuento
        if (datosActualizarDescuento.id_categoria() != null &&
                !datosActualizarDescuento.id_categoria().equals(categoria.getId())) {
            // Se intenta cambiar la categoría asociada al descuento
            categoria = categoriaRepository.findById(datosActualizarDescuento.id_categoria())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Nueva categoría no encontrada con ID: " + datosActualizarDescuento.id_categoria()));

        }

        if (Boolean.FALSE.equals(categoria.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede asociar el descuento a una categoría inactiva.");
        }

        // Obtener las fechas y porcentaje, usando las existentes si no se actualizan
        LocalDate fechaInicio = Optional.ofNullable(datosActualizarDescuento.fecha_inicio())
                .orElse(descuentoAActualizar.getFecha_inicio());
        LocalDate fechaFin = Optional.ofNullable(datosActualizarDescuento.fecha_fin())
                .orElse(descuentoAActualizar.getFecha_fin());

        // Validación 1: Fechas del descuento
        if (fechaFin.isBefore(fechaInicio)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de fin no puede ser anterior a la fecha de inicio del descuento.");
        }

        // Actualizar el descuento con los nuevos datos
        descuentoAActualizar.actualizar(datosActualizarDescuento, categoria); // Pasa la categoría actualizada

        // Reevaluar el estado activo basado en las nuevas fechas
        LocalDate hoy = LocalDate.now();
        if (descuentoAActualizar.getFecha_inicio().isAfter(hoy) || descuentoAActualizar.getFecha_fin().isBefore(hoy)) {
            descuentoAActualizar.setActivo(false); // Inactivo si es futuro o ya caducó
        } else {
            descuentoAActualizar.setActivo(true); // Activo si está en el rango de hoy
        }

        Descuento descuentoActualizado = descuentoRepository.save(descuentoAActualizar);
        return new DatosRespuestaDescuento(descuentoActualizado);
    }

    @Override
    public void activarDescuento(Long id) {
        Descuento descuento = descuentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Descuento no encontrado con el ID ingresado: " + id));

        if (Boolean.TRUE.equals(descuento.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El descuento ya está activo.");
        }

        if (Boolean.FALSE.equals(descuento.getId_categoria().getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede activar el descuento porque su categoría asociada está inactiva.");
        }

        // Las fechas deben estar dentro del rango actual (hoy debe estar
        // entre fecha_inicio y fecha_fin)
        LocalDate hoy = LocalDate.now();
        if (hoy.isBefore(descuento.getFecha_inicio()) || hoy.isAfter(descuento.getFecha_fin())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede activar el descuento fuera de su rango de fechas válido (" +
                            descuento.getFecha_inicio() + " a " + descuento.getFecha_fin() + ").");
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

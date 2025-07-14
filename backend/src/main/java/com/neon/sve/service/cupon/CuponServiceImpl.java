package com.neon.sve.service.cupon;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.cupon.DatosActualizarCupon;
import com.neon.sve.dto.cupon.DatosListadoCupon;
import com.neon.sve.dto.cupon.DatosRegistroCupon;
import com.neon.sve.dto.cupon.DatosRespuestaCupon;
import com.neon.sve.model.ventas.Cupon;
import com.neon.sve.repository.CuponRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CuponServiceImpl implements CuponService {

    @Autowired
    private CuponRepository cuponRepository;

    @Override
    public DatosRespuestaCupon crearCupon(DatosRegistroCupon datosRegistroCupon) {

        // Fechas del descuento
        LocalDate fechaInicio = datosRegistroCupon.fecha_inicio();
        LocalDate fechaFin = datosRegistroCupon.fecha_fin();
        LocalDate hoy = LocalDate.now();

        if (fechaFin.isBefore(fechaInicio)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de fin no puede ser anterior a la fecha de inicio del descuento.");
        }

        Cupon cupon = cuponRepository.save(new Cupon(datosRegistroCupon));
        return new DatosRespuestaCupon(cupon);

    }

    @Override
    public DatosRespuestaCupon actualizarCupon(DatosActualizarCupon datosActualizarCupon) {

        // Fechas del descuento
        LocalDate fechaInicio = datosActualizarCupon.fecha_inicio();
        LocalDate fechaFin = datosActualizarCupon.fecha_fin();
        LocalDate hoy = LocalDate.now();

        if (fechaFin.isBefore(fechaInicio)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de fin no puede ser anterior a la fecha de inicio del descuento.");
        }

        Cupon cupon = cuponRepository.getReferenceById(datosActualizarCupon.id());
        cupon.actualizar(datosActualizarCupon);
        cupon = cuponRepository.save(cupon);
        return new DatosRespuestaCupon(cupon);
    }

    @Override
    public Page<DatosListadoCupon> getAllCupones(Pageable pageable) {

        Page<Cupon> cuponPage = cuponRepository.findAll(pageable);
        return cuponPage.map(DatosListadoCupon::new);

    }

    @Override
    public DatosRespuestaCupon obtenerCuponPorId(Long id) {
        Optional<Cupon> cuponOptional = cuponRepository.findById(id);

        if (cuponOptional.isPresent()) {
            Cupon cupon = cuponOptional.get();
            return new DatosRespuestaCupon(cupon);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cupon no encontrado");

        }
    }

    @Override
    public void activarDescuento(Long id) {
        Cupon cupon = cuponRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Descuento no encontrado"));

        if (Boolean.TRUE.equals(cupon.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cupon ya esta activo");

        }

        cupon.setActivo(true);
        cuponRepository.save(cupon);
    }

    @Override
    public void desactivarDescuento(Long id) {

        Cupon cupon = cuponRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Descuento no encontrado"));

        if (!cupon.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cupon ya esta desaactivado");

        }

        cupon.setActivo(false);
        cuponRepository.save(cupon);
    }

    @Override
    public void incrementarUsoCupon(String codigo) {

        Cupon cupon = cuponRepository.findAll().stream()
                .filter(c -> c.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cupón no encontrado"));

        if (cupon.getUsosActuales() >= cupon.getMaxUsos()) {
            throw new IllegalStateException("El cupón ya alcanzó su límite de usos");
        }

        cupon.setUsosActuales(cupon.getUsosActuales() + 1);
        cuponRepository.save(cupon);

    }

}

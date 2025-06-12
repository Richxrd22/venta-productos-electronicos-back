package com.neon.sve.service.reclamoGarantia;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.reclamoGarantia.DatosActualizarReclamoGarantia;
import com.neon.sve.dto.reclamoGarantia.DatosListadoReclamoGarantia;
import com.neon.sve.dto.reclamoGarantia.DatosRegistroReclamoGarantia;
import com.neon.sve.dto.reclamoGarantia.DatosRespuestaReclamoGarantia;
import com.neon.sve.model.ventas.Garantia;
import com.neon.sve.model.ventas.ReclamoGarantia;
import com.neon.sve.model.ventas.Tipos.EstadoReclamo;
import com.neon.sve.repository.GarantiaRepository;
import com.neon.sve.repository.ReclamoGarantiaRepository;

@Service
public class ReclamoGarantiaServiceImpl implements ReclamoGarantiaService {

    @Autowired
    private GarantiaRepository garantiaRepository;

    @Autowired
    private ReclamoGarantiaRepository reclamoGarantiaRepository;

    @Override
    public DatosRespuestaReclamoGarantia createReclamoGarantia(
            DatosRegistroReclamoGarantia datosRegistrarReclamoGarantia) {

        Garantia garantia = garantiaRepository.findById(datosRegistrarReclamoGarantia.id_garantia())
                .orElseThrow(() -> new RuntimeException("Garantía no encontrada"));

        LocalDate hoy = LocalDate.now();
        if (hoy.isBefore(garantia.getInicioGarantia()) || hoy.isAfter(garantia.getFinGarantia())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede registrar el reclamo fuera del periodo de garantía.");
        }

        ReclamoGarantia reclamoGarantia = new ReclamoGarantia(datosRegistrarReclamoGarantia, garantia);
        reclamoGarantiaRepository.save(reclamoGarantia);

        return new DatosRespuestaReclamoGarantia(reclamoGarantia);
    }

    @Override
    public DatosRespuestaReclamoGarantia updateReclamoGarantia(
            DatosActualizarReclamoGarantia datosActuliazarReclamoGarantia) {

        ReclamoGarantia reclamoGarantia = reclamoGarantiaRepository
                .findById(datosActuliazarReclamoGarantia.id_reclamo_garantia())
                .orElseThrow(() -> new RuntimeException("Reclamo de garantía no encontrado"));

        Garantia garantia = garantiaRepository.findById(datosActuliazarReclamoGarantia.id_garantia())
                .orElseThrow(() -> new RuntimeException("Garantía no encontrada"));

        if (!Boolean.TRUE.equals(garantia.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La garantía está inactiva.");
        }

        LocalDate hoy = LocalDate.now();
        if (hoy.isBefore(garantia.getInicioGarantia()) || hoy.isAfter(garantia.getFinGarantia())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede actualizar el reclamo fuera del periodo de garantía.");
        }

        reclamoGarantia.actualizar(datosActuliazarReclamoGarantia, garantia);
        reclamoGarantiaRepository.save(reclamoGarantia);

        return new DatosRespuestaReclamoGarantia(reclamoGarantia);
    }

    @Override
    public DatosRespuestaReclamoGarantia getReclamoGarantiaById(Long id) {

        Optional<ReclamoGarantia> reclamoGarantiaOptional = reclamoGarantiaRepository.findById(id);
        if (reclamoGarantiaOptional.isPresent()) {
            ReclamoGarantia reclamoGarantia = reclamoGarantiaOptional.get();
            return new DatosRespuestaReclamoGarantia(reclamoGarantia);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reclamo de garantía no encontrado");
        }
    }

    @Override
    public Page<DatosListadoReclamoGarantia> getAllReclamoGarantia(Pageable pageable) {
        Page<ReclamoGarantia> reclamosGarantiaPage = reclamoGarantiaRepository.findAll(pageable);
        return reclamosGarantiaPage.map(reclamo -> new DatosListadoReclamoGarantia(reclamo));
    }

    @Override
    public void activarReclamoGarantia(Long id) {
        ReclamoGarantia reclamo = reclamoGarantiaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Reclamo de garantía no encontrado con ID: " + id));

        if (Boolean.TRUE.equals(reclamo.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El reclamo ya está activo.");
        }

        reclamo.setActivo(true);
        reclamoGarantiaRepository.save(reclamo);
    }

    @Override
    public void desactivarReclamoGarantia(Long id) {
        ReclamoGarantia reclamo = reclamoGarantiaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Reclamo de garantía no encontrado con ID: " + id));

        if (!EstadoReclamo.PENDIENTE.equals(reclamo.getEstado())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Solo se pueden desactivar reclamos en estado PENDIENTE.");
        }

        if (Boolean.FALSE.equals(reclamo.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El reclamo ya está desactivado.");
        }

        reclamo.setActivo(false);
        reclamoGarantiaRepository.save(reclamo);
    }

}

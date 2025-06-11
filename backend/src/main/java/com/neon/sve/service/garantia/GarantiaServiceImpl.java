package com.neon.sve.service.garantia;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.garantia.DatosActualizarGarantia;
import com.neon.sve.dto.garantia.DatosListadoGarantia;
import com.neon.sve.dto.garantia.DatosRegistroGarantia;
import com.neon.sve.dto.garantia.DatosRespuestaGarantia;
import com.neon.sve.model.ventas.DetalleVenta;
import com.neon.sve.model.ventas.Garantia;
import com.neon.sve.repository.DetalleVentaRepository;
import com.neon.sve.repository.GarantiaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GarantiaServiceImpl implements GarantiaService {

    @Autowired
    private GarantiaRepository garantiaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public DatosRespuestaGarantia createGarantia(DatosRegistroGarantia datosRegistro) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(datosRegistro.id_detalle_venta())
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));
        Garantia garantia = new Garantia(datosRegistro, detalleVenta);
        garantia = garantiaRepository.save(garantia);
        return new DatosRespuestaGarantia(garantia);
    }

    @Override
    public Page<DatosListadoGarantia> listarPorDetalleVenta(Pageable pageable) {
        Page<Garantia> empleadoPage = garantiaRepository.findAll(pageable);
        return empleadoPage.map(DatosListadoGarantia::new);
    }

    @Override
    public DatosRespuestaGarantia buscarPorId(Long id) {
        Optional<Garantia> garantiaOptional = garantiaRepository.findById(id);
        if (garantiaOptional.isPresent()) {
            Garantia garantia = garantiaOptional.get();
            return new DatosRespuestaGarantia(garantia);
        } else {
            throw new RuntimeException("Empleado no encontrado");
        }
    }

    @Override
    public DatosRespuestaGarantia updateGarantia(DatosActualizarGarantia datosActualizarGarantia) {
        Garantia garantia = garantiaRepository.findById(datosActualizarGarantia.id_garantia())
                .orElseThrow(() -> new RuntimeException("Garantía no encontrada"));

        DetalleVenta detalleVenta = detalleVentaRepository.findById(datosActualizarGarantia.id_detalle_venta())
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));

        garantia.actualizar(datosActualizarGarantia, detalleVenta);
        garantia = garantiaRepository.save(garantia);
        return new DatosRespuestaGarantia(garantia);
    }

    @Override
    @Transactional
    public void activarGarantia(Long id) {
        Garantia garantia = garantiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Garantía no encontrada con el ID ingresado: " + id));

        if (garantia.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La garantía ya está activa.");
        }

        garantia.setActivo(true);
    }

    @Override
    @Transactional
    public void desactivarGarantia(Long id) {
        Garantia garantia = garantiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Garantía no encontrada con el ID ingresado: " + id));

        if (!garantia.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La garantía ya está desactivada.");
        }

        garantia.setActivo(false);
    }

}

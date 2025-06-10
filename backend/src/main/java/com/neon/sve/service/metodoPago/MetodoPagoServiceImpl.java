package com.neon.sve.service.metodoPago;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.metodoPago.DatosActualizarMetodoPago;
import com.neon.sve.dto.metodoPago.DatosListadoMetodoPago;
import com.neon.sve.dto.metodoPago.DatosRegistroMetodoPago;
import com.neon.sve.dto.metodoPago.DatosRespuestaMetodoPago;
import com.neon.sve.model.ventas.MetodoPago;
import com.neon.sve.repository.MetodoPagoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MetodoPagoServiceImpl implements MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Override
    public DatosRespuestaMetodoPago getMetodoPagoById(Long id) {
        Optional<MetodoPago> metodoPagoOptional = metodoPagoRepository.findById(id);
        if (metodoPagoOptional.isPresent()) {
            MetodoPago metodoPago = metodoPagoOptional.get();
            return new DatosRespuestaMetodoPago(metodoPago);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Metodo de pago no encontrado");
        }
    }

    @Override
    public Page<DatosListadoMetodoPago> getAllMetodoPago(Pageable pageable) {
        Page<MetodoPago> metodoPagoPage = metodoPagoRepository.findAll(pageable);
        return metodoPagoPage.map(DatosListadoMetodoPago::new);
    }

    @Override
    public DatosRespuestaMetodoPago createMetodoPago(DatosRegistroMetodoPago datosRegistroMetodoPago) {
        Optional<MetodoPago> metodoPagoOptional = metodoPagoRepository.findByMetodo(datosRegistroMetodoPago.metodo());
        if (metodoPagoOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe un Metodo de Pago con el nombre ingresado, verifique o intentato nuevamente : "
                            + datosRegistroMetodoPago.metodo());
        }
        MetodoPago metodoPago = metodoPagoRepository.save(new MetodoPago(datosRegistroMetodoPago));
        return new DatosRespuestaMetodoPago(metodoPago);

    }

    @Override
    public DatosRespuestaMetodoPago updateMetodoPago(DatosActualizarMetodoPago datosActualizarMetodoPago) {
        Optional<MetodoPago> metodoPagoOptional = metodoPagoRepository.findByMetodo(datosActualizarMetodoPago.metodo());
        if (metodoPagoOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe un Metodo de Pago con el nombre ingresado, verifique o intentato nuevamente : "
                            + datosActualizarMetodoPago.metodo());
        }
        MetodoPago metodoPago = metodoPagoRepository.getReferenceById(datosActualizarMetodoPago.id());
        metodoPago.actualizar(datosActualizarMetodoPago);
        metodoPago = metodoPagoRepository.save(metodoPago);
        return new DatosRespuestaMetodoPago(metodoPago);
    }

    @Override
    public void activarMetodoPago(Long id) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Metodo de pago no encontrado, verifique ID ingresado : " + id));

        if (Boolean.TRUE.equals(metodoPago.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El metodo de pago ya esta activo");

        }
        metodoPago.setActivo(true);
        metodoPagoRepository.save(metodoPago);
    }

    @Override
    public void desactivarMetodoPago(Long id) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Metodo de pago no encontrado, verifique ID ingresado : " + id));

        if (!metodoPago.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El metodo de pago ya esta desactivado");

        }
        metodoPago.setActivo(false);
        metodoPagoRepository.save(metodoPago);
    }

}

package com.neon.sve.service.metodoPago;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.metodoPago.DatosActualizarMetodoPago;
import com.neon.sve.dto.metodoPago.DatosListadoMetodoPago;
import com.neon.sve.dto.metodoPago.DatosRegistroMetodoPago;
import com.neon.sve.dto.metodoPago.DatosRespuestaMetodoPago;

public interface MetodoPagoService {

    DatosRespuestaMetodoPago getMetodoPagoById(Long id);

    Page<DatosListadoMetodoPago> getAllMetodoPago(Pageable pageable);

    DatosRespuestaMetodoPago createMetodoPago(DatosRegistroMetodoPago datosRegistroMetodoPago);

    DatosRespuestaMetodoPago updateMetodoPago(DatosActualizarMetodoPago datosActualizarMetodoPago);

    void activarMetodoPago(Long id);

    void desactivarMetodoPago(Long id);

}

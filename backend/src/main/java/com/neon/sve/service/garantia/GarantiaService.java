package com.neon.sve.service.garantia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.garantia.DatosActualizarGarantia;
import com.neon.sve.dto.garantia.DatosListadoGarantia;
import com.neon.sve.dto.garantia.DatosRegistroGarantia;
import com.neon.sve.dto.garantia.DatosRespuestaGarantia;

public interface GarantiaService {
    DatosRespuestaGarantia createGarantia(DatosRegistroGarantia datosRegistro);

    Page<DatosListadoGarantia> listarPorDetalleVenta( Pageable pageable);

    DatosRespuestaGarantia buscarPorId(Long id);
    DatosRespuestaGarantia updateGarantia(DatosActualizarGarantia datosActualizarGarantia);

    void activarGarantia(Long id);
    void desactivarGarantia(Long id);
}

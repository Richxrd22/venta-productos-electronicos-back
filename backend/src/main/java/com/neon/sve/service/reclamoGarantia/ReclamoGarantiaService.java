package com.neon.sve.service.reclamoGarantia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.reclamoGarantia.DatosActualizarReclamoGarantia;
import com.neon.sve.dto.reclamoGarantia.DatosListadoReclamoGarantia;
import com.neon.sve.dto.reclamoGarantia.DatosRegistroReclamoGarantia;
import com.neon.sve.dto.reclamoGarantia.DatosRespuestaReclamoGarantia;

public interface ReclamoGarantiaService {
    
    DatosRespuestaReclamoGarantia createReclamoGarantia(DatosRegistroReclamoGarantia datosRegistrarReclamoGarantia);

    DatosRespuestaReclamoGarantia updateReclamoGarantia(DatosActualizarReclamoGarantia datosActuliazarReclamoGarantia);

    DatosRespuestaReclamoGarantia getReclamoGarantiaById(Long id);

    Page<DatosListadoReclamoGarantia> getAllReclamoGarantia(Pageable pageable);

    void activarReclamoGarantia(Long id);

    void desactivarReclamoGarantia(Long id);
}

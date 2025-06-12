package com.neon.sve.service.registroVenta;

import com.neon.sve.dto.registroVenta.DatosActualizarRegistroVenta;
import com.neon.sve.dto.registroVenta.DatosActualizarVentaCompleta;
import com.neon.sve.dto.registroVenta.DatosListadoRegistroVenta;
import com.neon.sve.dto.registroVenta.DatosRegistroVenta;
import com.neon.sve.dto.registroVenta.DatosRegistroVentaCompleta;
import com.neon.sve.dto.registroVenta.DatosRespuestaRegistroVenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VentaService {
    DatosRespuestaRegistroVenta getVentaById(Long id);

    Page<DatosListadoRegistroVenta> getAllVentas(Pageable pageable);

    // MÉTODO NUEVO Y RECOMENDADO
    DatosRespuestaRegistroVenta createVentaCompleta(DatosRegistroVentaCompleta datosRegistroVenta);

    // método antiguo
    // DatosRespuestaRegistroVenta createVenta(DatosRegistroVenta
    // datosRegistroVenta);

    // MÉTODO NUEVO
    DatosRespuestaRegistroVenta updateVentaCompleta(DatosActualizarVentaCompleta datosActualizar);

    // método antiguo
    // DatosRespuestaRegistroVenta updateVenta(DatosActualizarRegistroVenta
    // datosActualizarRegistroVenta);

    void cancelarVenta(Long id);

    void activarVenta(Long id);
}

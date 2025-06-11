package com.neon.sve.service.registroVenta;

import com.neon.sve.dto.registroVenta.DatosActualizarRegistroVenta;
import com.neon.sve.dto.registroVenta.DatosListadoRegistroVenta;
import com.neon.sve.dto.registroVenta.DatosRegistroVenta;
import com.neon.sve.dto.registroVenta.DatosRespuestaRegistroVenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VentaService {
    DatosRespuestaRegistroVenta getVentaById(Long id);

    Page<DatosListadoRegistroVenta> getAllVentas(Pageable pageable);

    DatosRespuestaRegistroVenta createVenta(DatosRegistroVenta datosRegistroVenta);

    DatosRespuestaRegistroVenta updateVenta(DatosActualizarRegistroVenta datosActualizarRegistroVenta);

    void cancelarVenta(Long id);

    void activarVenta(Long id);
}

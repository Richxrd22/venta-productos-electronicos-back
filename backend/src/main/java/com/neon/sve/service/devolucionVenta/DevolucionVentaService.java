package com.neon.sve.service.devolucionVenta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.devolucionVenta.DatosActualizarDevolucionVenta;
import com.neon.sve.dto.devolucionVenta.DatosListadoDevolucionVenta;
import com.neon.sve.dto.devolucionVenta.DatosRegistroDevolucionVenta;
import com.neon.sve.dto.devolucionVenta.DatosRespuestaDevolucionVenta;

public interface DevolucionVentaService {

    DatosRespuestaDevolucionVenta getDevolucionVentaById(Long id);

    Page<DatosListadoDevolucionVenta> getAllDevolucionVenta(Pageable pageable);

    DatosRespuestaDevolucionVenta createDevolucionVenta(DatosRegistroDevolucionVenta datosRegistrarDevolucionVenta);

    DatosRespuestaDevolucionVenta updateDevolucionVenta(DatosActualizarDevolucionVenta datosActualizarDevolucionVenta);

}

package com.neon.sve.service.devolucionProducto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.devolucionProducto.DatosActualizarDevolucionProducto;
import com.neon.sve.dto.devolucionProducto.DatosListadoDevolucionProducto;
import com.neon.sve.dto.devolucionProducto.DatosRegistroDevolucionProducto;
import com.neon.sve.dto.devolucionProducto.DatosRespuestaDevolucionProducto;
import com.neon.sve.dto.login.DatosRespuestaMensaje;

public interface DevolucionProductoService {
    DatosRespuestaDevolucionProducto getDevolucionProductoById(Long id);

    // Listar devoluciones con paginación
    Page<DatosListadoDevolucionProducto> getAllDevoluciones(Pageable pageable);

    // Registrar una nueva devolución
    DatosRespuestaMensaje registrarDevolucionProducto(DatosRegistroDevolucionProducto datos);

    DatosRespuestaMensaje actualizarDevolucionProducto(DatosActualizarDevolucionProducto datos);

    void activarDevolucionProducto(Long id);
    void desactivarDevolucionProducto(Long id);
}

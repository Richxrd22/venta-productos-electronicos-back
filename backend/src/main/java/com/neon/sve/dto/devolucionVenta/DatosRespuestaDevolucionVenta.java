package com.neon.sve.dto.devolucionVenta;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import com.neon.sve.dto.usuario.DatosListadoUsuario;
import com.neon.sve.model.ventas.DevolucionVenta;

// DTO de Respuesta Actualizado
public record DatosRespuestaDevolucionVenta(
                Long id,
                Long id_venta,
                Timestamp fecha,
                String motivo,
                DatosListadoUsuario usuario,
                String estado,
                List<DatosRespuestaDetalleDevolucion> detalles) {
        public DatosRespuestaDevolucionVenta(DevolucionVenta devolucion) {
                this(
                                devolucion.getId(),
                                devolucion.getVenta().getId(),
                                devolucion.getFecha(),
                                devolucion.getMotivo(),
                                new DatosListadoUsuario(devolucion.getUsuario()),
                                devolucion.getEstado().name(),
                                devolucion.getDetallesDevolucion().stream()
                                                .map(DatosRespuestaDetalleDevolucion::new)
                                                .collect(Collectors.toList()));
        }
}
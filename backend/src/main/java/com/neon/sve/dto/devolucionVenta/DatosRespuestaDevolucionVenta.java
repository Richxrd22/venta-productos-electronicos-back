package com.neon.sve.dto.devolucionVenta;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.neon.sve.dto.detalleVenta.DatosListadoDetalleVenta;
import com.neon.sve.dto.usuario.DatosListadoUsuario;
import com.neon.sve.model.ventas.DevolucionVenta;

public record DatosRespuestaDevolucionVenta(

                Long id,
                List<DatosListadoDetalleVenta> detallesVenta,
                Timestamp fecha,
                int cantidad,
                String motivo,
                List<DatosListadoUsuario> usuarios,
                String estado) {
        public DatosRespuestaDevolucionVenta(DevolucionVenta devolucionVenta) {
                this(
                                devolucionVenta.getId(),
                                // Mapea la lista de detalle_venta a DatosListadoDetalleVenta
                                devolucionVenta.getDetalleVenta() != null
                                                ? List.of(new DatosListadoDetalleVenta(
                                                                devolucionVenta.getDetalleVenta()))
                                                : null,
                                devolucionVenta.getFecha(),
                                devolucionVenta.getCantidad(),
                                devolucionVenta.getMotivo(),
                                // Mapea la lista de usuarios a DatosListadoUsuario
                                devolucionVenta.getUsuario() != null
                                                ? List.of(new DatosListadoUsuario(devolucionVenta.getUsuario()))
                                                : null,
                                devolucionVenta.getEstado().name());
        }
}
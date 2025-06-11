package com.neon.sve.dto.detalleVenta;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.neon.sve.model.ventas.DetalleVenta;

public record DatosRespuestDetalleVenta(

        Long id,
        String nombreProducto,
        int cantidad,
        BigDecimal precioUnitario,
        BigDecimal total,
        Timestamp fechaCreacion,
        int activo
        ) {
    public DatosRespuestDetalleVenta(DetalleVenta detalleVenta) {
        this(
                detalleVenta.getId(),
                detalleVenta.getId_producto().getNombre(),
                detalleVenta.getCantidad(),
                detalleVenta.getPrecio_unitario(),
                detalleVenta.getTotal(),
                detalleVenta.getFecha_creacion(),
                detalleVenta.getActivo() != null && detalleVenta.getActivo() ? 1 : 0
        );
    }

}

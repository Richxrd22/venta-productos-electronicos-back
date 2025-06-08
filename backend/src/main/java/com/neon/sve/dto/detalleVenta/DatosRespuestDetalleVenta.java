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
                detalleVenta.getPrecioUnitario(),
                detalleVenta.getTotal(),
                detalleVenta.getFechaCreacion(),
                detalleVenta.getActivo() != null && detalleVenta.getActivo() ? 1 : 0
        );
    }

}

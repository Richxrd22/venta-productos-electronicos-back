package com.neon.sve.dto.detalleVenta;

import java.sql.Timestamp;

import com.neon.sve.model.ventas.DetalleVenta;

public record DatosListadoDetalleVenta(

    Long id,
        String nombreProducto,
        int cantidad,
        double precioUnitario,
        double total,
        Timestamp fechaCreacion,
        int activo
        ) {
    public DatosListadoDetalleVenta(DetalleVenta detalleVenta) {
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

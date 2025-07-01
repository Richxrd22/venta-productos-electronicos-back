package com.neon.sve.dto.devolucionVenta;

import com.neon.sve.model.ventas.DetalleDevolucion;

public record DatosRespuestaDetalleDevolucion(
        Long id_producto,
        String nombreProducto,
        int cantidad) {
    public DatosRespuestaDetalleDevolucion(DetalleDevolucion detalle) {
        this(detalle.getProducto().getId(), detalle.getProducto().getNombre(), detalle.getCantidad());
    }
}
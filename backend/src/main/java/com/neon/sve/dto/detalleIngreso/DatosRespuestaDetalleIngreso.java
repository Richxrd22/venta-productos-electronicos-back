package com.neon.sve.dto.detalleIngreso;

import java.math.BigDecimal;

import com.neon.sve.model.stock.DetalleIngreso;

public record DatosRespuestaDetalleIngreso(
        Long id,
        String nombreProducto,
        String modelo,
        String lote,
        int cantidad,
        BigDecimal precioUnitario,
        BigDecimal subtotal

) {
    public DatosRespuestaDetalleIngreso(DetalleIngreso detalleIngreso) {
        this(
                detalleIngreso.getId(),
                detalleIngreso.getId_producto().getNombre(),
                detalleIngreso.getId_producto().getModelo(),
                detalleIngreso.getCodigo_detalle(),
                detalleIngreso.getCantidad(),
                detalleIngreso.getPrecio_unitario(),
                detalleIngreso.getSubtotal());
    }
    
}

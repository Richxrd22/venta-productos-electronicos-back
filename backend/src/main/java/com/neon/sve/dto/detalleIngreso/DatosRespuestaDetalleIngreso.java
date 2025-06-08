package com.neon.sve.dto.detalleIngreso;

import java.sql.Timestamp;
import java.util.stream.Collectors;

import com.neon.sve.model.stock.DetalleIngreso;

public record DatosRespuestaDetalleIngreso(
        Long id,
        Timestamp fecha_ingreso,
        String lote,
        String nombreProducto,
        String modelo,
        String codigoLote,
        int cantidad,
        double precioUnitario,
        double subtotal

) {
    public DatosRespuestaDetalleIngreso(DetalleIngreso detalleIngreso) {
        this(
                detalleIngreso.getId(),
                detalleIngreso.getId_ingresoStock().getFecha_ingreso(),
                detalleIngreso.getId_ingresoStock().getLote(),
                detalleIngreso.getId_producto().getNombre(),
                detalleIngreso.getId_producto().getModelo(),
                detalleIngreso.getCodigoLote(),
                detalleIngreso.getCantidad(),
                detalleIngreso.getPrecioUnitario(),
                detalleIngreso.getSubtotal());
    }
    
}

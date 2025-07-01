package com.neon.sve.dto.detalleIngreso;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.neon.sve.model.stock.DetalleIngreso;

public record DatosListadoDetalleIngreso(

        Long id,
        String nombreProducto,
        String modelo,
        String lote,
        int cantidad,
        BigDecimal precioUnitario,
        BigDecimal subtotal

) {

    public DatosListadoDetalleIngreso(DetalleIngreso detalleIngreso) {

        this(
                detalleIngreso.getId(),
                detalleIngreso.getId_producto().getNombre(),
                detalleIngreso.getId_producto().getModelo(),
                detalleIngreso.getCodigoLote(),
                detalleIngreso.getCantidad(),
                detalleIngreso.getPrecio_unitario(),
                detalleIngreso.getSubtotal());
    }

}

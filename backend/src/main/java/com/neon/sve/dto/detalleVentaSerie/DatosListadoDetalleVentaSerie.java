package com.neon.sve.dto.detalleVentaSerie;

import java.sql.Timestamp;

import com.neon.sve.model.ventas.DetalleVentaSeries;

public record DatosListadoDetalleVentaSerie(

        Long id,
        int cantidadDetalle,
        double totalDetalle,
        Timestamp fechaCreacion,
        String numeroSerie) {
    public DatosListadoDetalleVentaSerie(DetalleVentaSeries detalleVentaSerie) {
        this(
                detalleVentaSerie.getId(),
                detalleVentaSerie.getId_detalle_venta().getCantidad(),
                detalleVentaSerie.getId_detalle_venta().getTotal(),
                detalleVentaSerie.getId_detalle_venta().getFechaCreacion(),
                detalleVentaSerie.getId_serie_producto().getNumeroSerie());
    }

}

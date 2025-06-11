package com.neon.sve.dto.detalleVentaSerie;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.neon.sve.model.ventas.DetalleVentaSeries;

public record DatosListadoDetalleVentaSerie(

        Long id,
        int cantidadDetalle,
        BigDecimal totalDetalle,
        Timestamp fechaCreacion,
        String numeroSerie) {
    public DatosListadoDetalleVentaSerie(DetalleVentaSeries detalleVentaSerie) {
        this(
                detalleVentaSerie.getId(),
                detalleVentaSerie.getId_detalle_venta().getCantidad(),
                detalleVentaSerie.getId_detalle_venta().getTotal(),
                detalleVentaSerie.getId_detalle_venta().getFecha_creacion(),
                detalleVentaSerie.getId_serie_producto().getNumeroSerie());
    }

}

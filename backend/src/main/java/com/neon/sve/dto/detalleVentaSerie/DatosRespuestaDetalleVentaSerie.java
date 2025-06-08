package com.neon.sve.dto.detalleVentaSerie;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.neon.sve.model.ventas.DetalleVentaSeries;

public record DatosRespuestaDetalleVentaSerie(

        Long id,
        int cantidadDetalle,
        BigDecimal totalDetalle,
        Timestamp fechaCreacion,
        String numeroSerie) {
    public DatosRespuestaDetalleVentaSerie(DetalleVentaSeries detalleVentaSerie) {
        this(
                detalleVentaSerie.getId(),
                detalleVentaSerie.getId_detalle_venta().getCantidad(),
                detalleVentaSerie.getId_detalle_venta().getTotal(),
                detalleVentaSerie.getId_detalle_venta().getFechaCreacion(),
                detalleVentaSerie.getId_serie_producto().getNumeroSerie());
    }

}

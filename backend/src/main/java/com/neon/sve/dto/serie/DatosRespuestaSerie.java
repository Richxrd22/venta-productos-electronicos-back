package com.neon.sve.dto.serie;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.neon.sve.model.stock.SerieProducto;

public record DatosRespuestaSerie(
        Long id,
        String codigoLote,
        int cantidad,
        BigDecimal precioUni,
        String numeroSerie,
        String estado,
        Timestamp fechaRegistro) {
    public DatosRespuestaSerie(SerieProducto serieProducto) {
        this(
                serieProducto.getId(),
                serieProducto.getId_detalle_ingreso().getCodigoLote(),
                serieProducto.getId_detalle_ingreso().getCantidad(),
                serieProducto.getId_detalle_ingreso().getPrecio_unitario(),
                serieProducto.getNumeroSerie(),
                serieProducto.getEstado().name(),
                serieProducto.getFechaRegistro());
    }
}

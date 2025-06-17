package com.neon.sve.dto.serie;

import com.neon.sve.model.stock.SerieProducto;

public record DatosListadoSerieDetalle(
        Long id_serie_producto,
        String numeroSerie,
        String estado) {

    public DatosListadoSerieDetalle(SerieProducto serieProducto) {
        this(
                serieProducto.getId(),
                serieProducto.getNumeroSerie(),
                serieProducto.getEstado().name());
    }
}
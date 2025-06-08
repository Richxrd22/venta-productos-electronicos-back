package com.neon.sve.dto.serie;

import java.sql.Timestamp;

import com.neon.sve.model.stock.SerieProducto;

public record DatosListadoSerie(

        Long id,
        String codigoLote,
        int cantidad,
        double precioUni,
        String numeroSerie,
        String estado,
        Timestamp fechaRegistro) {
    public DatosListadoSerie(SerieProducto serieProducto) {
        this(
                serieProducto.getId(),
                serieProducto.getId_detalle_ingreso().getCodigoLote(),
                serieProducto.getId_detalle_ingreso().getCantidad(),
                serieProducto.getId_detalle_ingreso().getPrecioUnitario(),
                serieProducto.getNumeroSerie(),
                serieProducto.getEstado().name(),
                serieProducto.getFechaRegistro());
    }

}

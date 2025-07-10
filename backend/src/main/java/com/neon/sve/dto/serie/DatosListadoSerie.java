package com.neon.sve.dto.serie;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neon.sve.model.stock.SerieProducto;

public record DatosListadoSerie(

        Long id_serie_producto,
        Long id_detalle_ingreso,
        String codigoLote,
        String producto,
        String numeroSerie,
        String estado,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") Timestamp fechaRegistro) {
    public DatosListadoSerie(SerieProducto serieProducto) {
        this(
                serieProducto.getId(),
                serieProducto.getId_detalle_ingreso().getId(),
                serieProducto.getId_detalle_ingreso().getCodigo_detalle(),
                serieProducto.getId_detalle_ingreso().getId_producto().getNombre(),
                serieProducto.getNumeroSerie(),
                serieProducto.getEstado().name(),
                serieProducto.getFechaRegistro());
    }

}

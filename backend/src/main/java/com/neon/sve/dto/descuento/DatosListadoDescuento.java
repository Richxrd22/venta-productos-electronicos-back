package com.neon.sve.dto.descuento;


import java.time.LocalDate;

import com.neon.sve.model.ventas.Descuento;

public record DatosListadoDescuento(

    Long id,
    String nombreCategoria,
    double porcentaje,
    LocalDate fecha_inicio,
    LocalDate fecha_final,
    boolean activo


) {

    public DatosListadoDescuento(Descuento descuento){

        this(
        descuento.getId(),
        descuento.getId_categoria().getNombre(),
        descuento.getPorcentaje(),
        descuento.getFecha_inicio(),
        descuento.getFecha_fin(),
        descuento.getActivo());

    }
    
}

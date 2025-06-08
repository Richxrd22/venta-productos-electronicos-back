package com.neon.sve.dto.descuento;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroDescuento(

    @NotNull Long id_categoria,
    @NotBlank String nombre,
    @NotNull Double procentaje,
    @NotNull LocalDate fecha_inicio,
    @NotNull LocalDate fecha_fin


) {

    
}

package com.neon.sve.dto.descuento;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarDescuento(

        @NotNull Long id,
        @NotNull Long id_categoria,
        @NotNull Double porcentaje,
        @NotNull LocalDate fecha_inicio,
        @NotNull LocalDate fecha_fin

) {

}

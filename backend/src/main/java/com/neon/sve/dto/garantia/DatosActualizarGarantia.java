package com.neon.sve.dto.garantia;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarGarantia(

        @NotNull Long id,
        @NotNull Long id_detalleVenta,
        @NotNull LocalDate inicioGarantia,
        @NotNull LocalDate finGarantia

) {

}

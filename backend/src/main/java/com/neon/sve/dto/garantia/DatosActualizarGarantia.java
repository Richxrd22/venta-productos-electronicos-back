package com.neon.sve.dto.garantia;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarGarantia(

        @NotNull Long id_garantia,
        @NotNull Long id_detalle_venta,
        @NotNull LocalDate inicio_garantia,
        @NotNull LocalDate fin_garantia

) {

}

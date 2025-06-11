package com.neon.sve.dto.garantia;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroGarantia(

    @NotNull Long id_detalle_venta,
    @NotNull LocalDate inicio_garantia,
    @NotNull LocalDate fin_garantia

) {
    
}

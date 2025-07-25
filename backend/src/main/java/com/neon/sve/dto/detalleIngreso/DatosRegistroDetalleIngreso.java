package com.neon.sve.dto.detalleIngreso;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosRegistroDetalleIngreso(

        @NotNull Long id_ingreso,
        @NotNull Long id_producto,
        @NotBlank String codigoLote,
        @NotNull @Min(1) int cantidad,
        @NotNull @Positive BigDecimal precio_unitario

) {

}

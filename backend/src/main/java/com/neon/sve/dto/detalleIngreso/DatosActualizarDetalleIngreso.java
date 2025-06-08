package com.neon.sve.dto.detalleIngreso;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosActualizarDetalleIngreso(

        @NotNull Long id,
        @NotNull Long id_ingreso,
        @NotNull Long id_producto,
        @NotBlank String codigoLote,
        @NotNull @Min(0) int cantidad,
        @NotNull @Positive double precioUnitario,
        @NotNull double subtotal

) {

}

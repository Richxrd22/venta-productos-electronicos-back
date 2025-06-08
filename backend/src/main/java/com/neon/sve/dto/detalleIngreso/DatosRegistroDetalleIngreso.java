package com.neon.sve.dto.detalleIngreso;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record DatosRegistroDetalleIngreso(

        @NotNull Long id_ingreso,
        @NotNull Long id_producto,
        @NotBlank String codigoLote,
        @NotNull @Min(1) int cantidad,
        @NotNull @Positive double precioUnitario,
        @NotNull double subtotal

) {

}

package com.neon.sve.dto.registroVenta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DatosActualizarRegistroVenta(

        @NotNull Long id,
        @NotNull double igvPorcentaje,
        @NotNull double subtotal,
        @PositiveOrZero Double descuento,
        @NotNull Boolean cancelado,
        @NotNull Long id_cliente,
        @NotNull Long id_metodoPago,
        Long id_cupon

) {

}

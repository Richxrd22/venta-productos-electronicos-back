package com.neon.sve.dto.registroVenta;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DatosRegistroVenta(

        @NotNull double igvPorcentaje,
        @NotNull double subtotal,
        // Nota: igvTotal y total serán calculados en el servicio generalmente.
        @PositiveOrZero Double descuento,
        @NotNull Boolean cancelado,
        @NotNull Long id_usuario,
        @NotNull Long id_cliente,
        @NotNull Long id_metodoPago,
        Long id_cupon

) {

}

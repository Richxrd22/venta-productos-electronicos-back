package com.neon.sve.dto.registroVenta;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DatosActualizarRegistroVenta(

        @NotNull Long id,
        @NotNull BigDecimal igv_porcentaje,
        @NotNull BigDecimal subtotal,
        @NotNull BigDecimal igv_total,
        @NotNull BigDecimal total,
        @PositiveOrZero BigDecimal descuento,
        @NotNull Boolean cancelado,
        @NotNull Long id_usuario,
        @NotNull Long id_cliente,
        @NotNull Long id_metodo_pago,
        Long id_cupon

) {

}

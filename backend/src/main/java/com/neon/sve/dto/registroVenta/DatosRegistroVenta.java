package com.neon.sve.dto.registroVenta;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DatosRegistroVenta(

        BigDecimal igv_porcentaje,
        BigDecimal subtotal,
        BigDecimal igv_total,
        BigDecimal total,
        @PositiveOrZero BigDecimal descuento,
        @NotNull Boolean cancelado,
        @NotNull Long id_usuario,
        @NotNull Long id_cliente,
        @NotNull Long id_metodo_pago,
        Long id_cupon

) {

}

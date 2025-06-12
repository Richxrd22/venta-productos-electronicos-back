package com.neon.sve.dto.registroVenta;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroItemVenta(

        @NotNull Long id_producto,

        @NotNull @Min(1) int cantidad,

        @NotNull BigDecimal precio_unitario

) {

}

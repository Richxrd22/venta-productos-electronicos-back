package com.neon.sve.dto.detalleVenta;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosRegistroDetalleVenta(

    @Min(1) int cantidad,
    @Positive BigDecimal precio_unitario,
    BigDecimal total,
    @NotNull Long id_producto,
    @NotNull Long id_registro_venta
    // total y fechaCreacion se calculan/generan automáticamente

) {
    
}

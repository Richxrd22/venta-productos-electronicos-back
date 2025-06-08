package com.neon.sve.dto.detalleVenta;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosRegistroDetalleVenta(

    @NotNull @Min(1) int cantidad,
    @NotNull @Positive double precioUnitario,
    @NotNull Long id_producto,
    @NotNull Long id_registroVenta
    // total y fechaCreacion se calculan/generan automáticamente

) {
    
}

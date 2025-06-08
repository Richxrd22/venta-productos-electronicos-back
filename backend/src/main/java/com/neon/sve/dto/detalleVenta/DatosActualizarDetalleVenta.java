package com.neon.sve.dto.detalleVenta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosActualizarDetalleVenta(

    @NotNull Long id,
    @NotNull int cantidad,
    @NotNull @Positive double precioUnitario
    //@NotNull Long id_producto,
    //@NotNull Long id_registroVenta

) {
    
}

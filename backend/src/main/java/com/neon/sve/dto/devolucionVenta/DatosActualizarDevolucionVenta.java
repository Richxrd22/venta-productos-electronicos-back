package com.neon.sve.dto.devolucionVenta;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarDevolucionVenta(

    @NotNull Long id,
    //@NotNull Long id_detalle,
    @NotNull int cantidad,
    String motivo
    //@NotNull Long id_usuario

) {
    
}

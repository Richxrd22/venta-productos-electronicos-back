package com.neon.sve.dto.devolucionProducto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarDevolucionProducto(

    @NotNull Long id,
    Long id_serie_producto,
    Long id_detalle_ingreso,
    @NotNull int cantidad,
    @NotBlank String motivo,
    String observaciones,
    @NotNull Long id_usuario,
    Boolean reposicionAplicada

) {
    
}

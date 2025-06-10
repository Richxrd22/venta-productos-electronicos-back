package com.neon.sve.dto.devolucionProducto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarDevolucionProducto(

        @NotNull Long id_devolucion_producto,
        @NotNull Integer cantidad,
        @NotBlank String motivo,
        String observaciones,
        @NotNull Long id_usuario,
        Boolean reposicion_aplicada

) {

}

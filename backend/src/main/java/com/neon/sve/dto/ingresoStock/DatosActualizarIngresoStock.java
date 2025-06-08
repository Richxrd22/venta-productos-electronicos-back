package com.neon.sve.dto.ingresoStock;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarIngresoStock(

        @NotNull Long id,
        @NotNull Long id_proveedor,
        @NotBlank String tipo_documento,
        @NotBlank String numero_documento,
        String observaciones,
        @NotBlank int cantidad,
        @NotNull Long id_producto,
        @NotNull Long id_usuario) {

}

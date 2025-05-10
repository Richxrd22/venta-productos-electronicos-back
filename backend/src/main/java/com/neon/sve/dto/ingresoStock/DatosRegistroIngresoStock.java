package com.neon.sve.dto.ingresoStock;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroIngresoStock(

        @NotNull Long id_producto,
        @NotNull Long id_proveedor,
        @NotNull Long id_usuario,
        @NotBlank String sku,
        @NotNull int cantidad,
        @NotNull Boolean activo) {

}

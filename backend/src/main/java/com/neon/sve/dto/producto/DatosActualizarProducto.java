package com.neon.sve.dto.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarProducto(

        @NotNull Long id,
        @NotBlank String nombre,
        @NotBlank String descripcion,
        @NotNull Double precio_venta,
        @NotNull Double precio_compra,
        @NotNull int min_stock,
        @NotNull int max_stock,
        @NotNull int garantia_meses,
        @NotNull Long id_usuario,
        @NotNull Long id_subcategoria,
        @NotNull Long id_marca) {

}

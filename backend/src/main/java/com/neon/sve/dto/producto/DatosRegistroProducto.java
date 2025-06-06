package com.neon.sve.dto.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroProducto(

        @NotBlank String nombre,
        String modelo,
        String color,
        @NotBlank String descripcion,
        @NotNull Double precio_venta,
        @NotNull Double precio_compra,
        @NotNull int min_stock,
        @NotNull int max_stock,
        @NotNull int garantia_meses,
        @NotNull Long id_usuario,
        @NotNull Long id_categoria,
        @NotNull Long id_marca

) {

}

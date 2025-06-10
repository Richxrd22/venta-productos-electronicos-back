package com.neon.sve.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarCategoria(

        @NotNull Long id,
        @NotBlank String nombre,
        Long id_categoria_padre,
        @NotNull int nivel) {

}

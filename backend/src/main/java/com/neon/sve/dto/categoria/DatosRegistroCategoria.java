package com.neon.sve.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCategoria(

        @NotBlank String nombre,
        @NotNull Long id_padre,
        @NotNull int nivel) {

}

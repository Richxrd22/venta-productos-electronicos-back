package com.neon.sve.dto.marca;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarMarca(

        @NotNull Long id,
        @NotBlank String nombre) {

}

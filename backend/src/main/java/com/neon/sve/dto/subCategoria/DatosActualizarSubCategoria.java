package com.neon.sve.dto.subCategoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarSubCategoria(

        @NotNull Long id,
        @NotBlank String nombre,
        @NotNull Boolean activo,
        @NotNull Long id_categoria

) {

}

package com.neon.sve.dto.subCategoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroSubCategoria(

        @NotBlank String nombre,
        @NotNull Long id_categoria

) {

}

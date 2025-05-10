package com.neon.sve.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCategoria(

        @NotBlank String nombre) {

}

package com.neon.sve.dto.empresa;

import jakarta.validation.constraints.NotBlank;


public record DatosRegistroEmpresa(

        @NotBlank String nombre,
        @NotBlank String ruc,
        @NotBlank String website

) {
}

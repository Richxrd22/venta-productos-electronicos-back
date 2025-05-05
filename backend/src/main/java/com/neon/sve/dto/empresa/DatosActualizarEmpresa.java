package com.neon.sve.dto.empresa;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarEmpresa(

        @NotNull Long id_empresa,
        @NotNull String nombre,
        @NotNull String ruc,
        @NotNull String website) {

}

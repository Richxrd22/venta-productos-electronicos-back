package com.neon.sve.dto.rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarRol(
        @NotNull Long id_rol,
        @NotBlank String nombre
        ) {
}
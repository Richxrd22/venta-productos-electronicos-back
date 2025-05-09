package com.neon.sve.dto.usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @NotBlank String clave,
        @Email @NotBlank String correo,
        @NotNull Long id_empleado,
        @NotNull Long id_rol,
        @NotNull Boolean clave_cambiada,
        @NotNull Boolean activo
        ) {
}
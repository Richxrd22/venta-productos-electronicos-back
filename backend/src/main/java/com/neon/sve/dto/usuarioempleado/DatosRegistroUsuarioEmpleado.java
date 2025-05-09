package com.neon.sve.dto.usuarioempleado;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuarioEmpleado(
    @NotBlank String nombre,
    @NotBlank String apellido,
    @NotBlank String dni,
    @Email String correo,
    @NotBlank String celular,
    @NotNull Boolean activo,
    @NotNull Long id_rol
) {
    
}

package com.neon.sve.dto.empleado;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarEmpleado(
    @NotNull Long id,
    @NotBlank String nombre,
    @NotBlank String apellido,
    @NotBlank String dni,
    @Email String correo,
    @NotBlank String celular,
    @NotNull Boolean activo
) {
    
}

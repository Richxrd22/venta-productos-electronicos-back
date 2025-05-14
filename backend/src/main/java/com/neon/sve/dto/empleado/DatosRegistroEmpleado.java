package com.neon.sve.dto.empleado;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroEmpleado(
    @NotBlank String nombre,
    @NotBlank String apellido,
    @NotBlank String dni,
    @Email String correo,
    @NotBlank String celular
) {
} 

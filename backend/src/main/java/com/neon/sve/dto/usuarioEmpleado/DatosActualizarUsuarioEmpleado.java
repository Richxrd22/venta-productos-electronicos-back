package com.neon.sve.dto.usuarioEmpleado;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuarioEmpleado(
        @NotNull Long id_empleado,
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotBlank String dni,
        @Email String correo,
        @NotBlank String celular,
        @NotNull Long id_rol

) {

}

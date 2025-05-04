package com.neon.sve.dto.login;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosLoginUsuario(

        @Email @NotBlank String correo_usuario,
        @NotBlank String clave) {

}

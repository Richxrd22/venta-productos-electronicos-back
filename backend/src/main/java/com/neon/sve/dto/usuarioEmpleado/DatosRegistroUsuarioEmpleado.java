package com.neon.sve.dto.usuarioEmpleado;


import com.neon.sve.dto.annotations.usuario.unique.celular.UniqueCelular;
import com.neon.sve.dto.annotations.usuario.unique.correo.UniqueCorreo;
import com.neon.sve.dto.annotations.usuario.unique.dni.UniqueDni;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuarioEmpleado(
    @NotBlank String nombre,
    @NotBlank String apellido,
    @NotBlank @UniqueDni String dni,
    @Email @UniqueCorreo String correo,
    @NotBlank @UniqueCelular String celular,
    @NotNull Long id_rol
) {
    
}

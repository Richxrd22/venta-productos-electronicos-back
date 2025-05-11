package com.neon.sve.dto.login;

import jakarta.validation.constraints.NotBlank;

public record DatosCambiarClave(
    @NotBlank String clave_actual,
    @NotBlank String clave_nueva
) {
    
}

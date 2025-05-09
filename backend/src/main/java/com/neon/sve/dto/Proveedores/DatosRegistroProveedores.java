package com.neon.sve.dto.Proveedores;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroProveedores(

    @NotBlank String razon_social,
    @NotBlank String ruc,
    @Email @NotBlank String correo,
    @NotBlank String direccion,
    @NotBlank String celular,
    @NotBlank String telefono
) {
    
}

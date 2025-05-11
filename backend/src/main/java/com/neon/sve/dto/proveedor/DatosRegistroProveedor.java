package com.neon.sve.dto.proveedor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroProveedor(

    @NotBlank String razon_social,
    @NotBlank String ruc,
    @NotBlank String direccion,
    @Email @NotBlank String correo,
    @NotBlank String celular,
    @NotBlank String telefono
) {
    
}

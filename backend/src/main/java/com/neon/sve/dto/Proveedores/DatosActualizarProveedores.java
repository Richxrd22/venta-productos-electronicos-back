package com.neon.sve.dto.Proveedores;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarProveedores(

    @NotNull Long id,
    @NotBlank String razon_social,
    @NotBlank String ruc,
    @Email @NotBlank String correo,
    @NotBlank String direccion,
    @NotBlank String telefono,
    @NotBlank String celular,
    @NotNull Boolean activo

) {
    
}

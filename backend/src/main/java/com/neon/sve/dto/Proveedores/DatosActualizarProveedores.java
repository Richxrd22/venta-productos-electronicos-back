package com.neon.sve.dto.Proveedores;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarProveedores(

    @NotNull Long id_proveedor,
    @NotBlank String nombre,
    @NotBlank String apellido,
    @Email @NotBlank String correo,
    @NotBlank String dni,
    @NotBlank String telefono,
    @NotBlank String celular,
    @NotNull Boolean estado,
    @NotNull Long id_empresa

) {
    
}

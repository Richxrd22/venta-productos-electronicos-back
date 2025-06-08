package com.neon.sve.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DatosActualizarCliente(

    @NotNull Long id,
    @NotBlank String nombre,
    @NotBlank String apellido,
    @NotBlank @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos") String dni,
    @NotBlank @Pattern(regexp = "\\d{9}", message = "El celular debe tener 9 dígitos") String celular,
    @NotBlank @Email @Size(max = 100) String correo

) {
    
}

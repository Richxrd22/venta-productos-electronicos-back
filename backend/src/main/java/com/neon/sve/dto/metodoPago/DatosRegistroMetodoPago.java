package com.neon.sve.dto.metodoPago;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroMetodoPago(
    
    @NotBlank String metodo

) {
    
}

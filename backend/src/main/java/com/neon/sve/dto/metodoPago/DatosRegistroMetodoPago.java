package com.neon.sve.dto.metodoPago;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DatosRegistroMetodoPago(
    
    @NotBlank String metodo

) {
    
}

package com.neon.sve.dto.metodoPago;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarMetodoPago(

    @NotNull Long id,
    @NotBlank String metodo

) {
    
}

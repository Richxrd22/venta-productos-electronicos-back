package com.neon.sve.dto.reclamoGarantia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroReclamoGarantia(

    @NotNull Long id_garantia,
    @NotBlank String descripcion

) {
    
}

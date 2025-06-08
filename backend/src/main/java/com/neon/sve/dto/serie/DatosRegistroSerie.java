package com.neon.sve.dto.serie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroSerie(

    @NotNull Long id_detalle_ingreso,
    @NotBlank String numeroSerie

) {
    
}

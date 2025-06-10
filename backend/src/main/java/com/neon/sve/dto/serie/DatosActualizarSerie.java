package com.neon.sve.dto.serie;

import com.neon.sve.model.stock.EstadoSerie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarSerie(

    @NotNull Long id,
    @NotNull Long id_detalle_ingreso,
    @NotBlank String numero_serie,
    @NotNull EstadoSerie estado

    ) {
    
}

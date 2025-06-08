package com.neon.sve.dto.reclamoGarantia;

import com.neon.sve.model.ventas.Tipos.EstadoReclamo;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarReclamoGarantia(

    @NotNull Long id,
    String descripcion,
    EstadoReclamo estado
    
) {
    
}

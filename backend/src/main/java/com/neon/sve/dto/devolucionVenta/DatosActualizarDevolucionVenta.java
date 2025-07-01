package com.neon.sve.dto.devolucionVenta;

import com.neon.sve.model.ventas.Tipos.EstadoReclamo;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarDevolucionVenta(

        @NotNull Long id,
        String motivo,
        EstadoReclamo estado

) {

}

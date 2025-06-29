package com.neon.sve.dto.devolucionVenta;

import com.neon.sve.model.ventas.Tipos.EstadoReclamo;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarDevolucionVenta(

    @NotNull Long id,
    @NotNull Long id_detalle_venta,
    @NotNull int cantidad,
    String motivo,
    EstadoReclamo estado

) {
    
}

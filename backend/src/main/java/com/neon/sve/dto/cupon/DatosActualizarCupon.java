package com.neon.sve.dto.cupon;

import java.time.LocalDate;

import com.neon.sve.model.ventas.Tipos.TipoDescuentoCupon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DatosActualizarCupon(

    @NotNull Long id,
    @NotBlank String codigo,
    String descripcion,
    @NotNull TipoDescuentoCupon tipoDescuento,
    @PositiveOrZero Double descuentoPorcentaje,
    @PositiveOrZero Double descuentoMonto,
    @NotNull LocalDate fechaInicio,
    @NotNull LocalDate fechaFin,
    @NotNull int maxUsos

) {
    
}

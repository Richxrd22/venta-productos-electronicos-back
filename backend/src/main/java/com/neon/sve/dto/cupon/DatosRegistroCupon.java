package com.neon.sve.dto.cupon;

import java.time.LocalDate;
import com.neon.sve.model.ventas.Tipos.TipoDescuentoCupon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCupon(

    @NotBlank String codigo,
    String descripcion,
    @NotNull TipoDescuentoCupon tipo_descuento,
    Double descuento_porcentaje,
    Double descuento_monto,
    @NotNull LocalDate fecha_inicio,
    @NotNull LocalDate fecha_fin,
    @NotNull int max_usos

) {
    
}

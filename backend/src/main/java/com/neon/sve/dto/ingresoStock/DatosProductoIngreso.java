package com.neon.sve.dto.ingresoStock;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosProductoIngreso(

@NotNull Long id_producto,

    @NotNull @Min(1)
    Integer cantidad,

    @NotNull
    @DecimalMin(value = "0.01", message = "El precio unitario debe ser mayor a cero")
    BigDecimal precio_unitario,

    @NotBlank
    String tipo_serie,  // "SIN_SERIE" o "CON_SERIE"

    List<String> series_individuales
) {
    
}

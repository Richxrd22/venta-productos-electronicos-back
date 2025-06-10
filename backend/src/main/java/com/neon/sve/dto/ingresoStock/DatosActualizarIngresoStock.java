package com.neon.sve.dto.ingresoStock;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarIngresoStock(
                @NotNull Long id_ingreso,
                @NotNull Long id_proveedor,
                @NotBlank String tipo_documento,
                @NotBlank String numero_documento,
                String observaciones,
                int cantidad_producto,
                @DecimalMin(value = "0.01", message = "El precio unitario debe ser mayor a cero") BigDecimal precio_unitario,
                @NotNull Long id_usuario) {

}

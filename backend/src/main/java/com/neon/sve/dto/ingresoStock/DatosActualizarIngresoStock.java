package com.neon.sve.dto.ingresoStock;

import java.util.List;

import com.neon.sve.dto.detalleIngreso.DatosActualizarDetalleIngreso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarIngresoStock(
                @NotNull Long id_ingreso,
                @NotNull Long id_proveedor,
                @NotBlank String tipo_documento,
                @NotBlank String numero_documento,
                String observaciones,
                @NotNull Long id_usuario,
                List<DatosActualizarDetalleIngreso> detalles
                
                ) {

}
  //              @DecimalMin(value = "0.01", message = "El precio unitario debe ser mayor a cero") BigDecimal precio_unitario,

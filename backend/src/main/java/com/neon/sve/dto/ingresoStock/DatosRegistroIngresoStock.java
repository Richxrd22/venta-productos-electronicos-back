package com.neon.sve.dto.ingresoStock;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;

public record DatosRegistroIngresoStock(

        @NotNull Long id_proveedor,
        @NotBlank String tipo_documento,
        @NotBlank String numero_documento,
        String observaciones,
        @NotNull int cantidad_producto,
        @NotNull Long id_usuario,
        @NotNull Long id_producto,
        @NotNull
        @DecimalMin(value = "0.01", message = "El precio unitario debe ser mayor a cero")
        BigDecimal precio_unitario,
        
         // NUEVOS CAMPOS PARA SERIES
        @NotBlank
        String tipo_serie,  // "SIN_SERIE","CON_SERIE"

        // Para series individuales (lista de números de serie)
        List<String> series_individuales

        ) {

}

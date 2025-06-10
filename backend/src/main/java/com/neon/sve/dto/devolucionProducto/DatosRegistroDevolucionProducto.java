package com.neon.sve.dto.devolucionProducto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroDevolucionProducto(

        @NotNull Long id_detalle_ingreso,

        // "CON_SERIE" o "SIN_SERIE"
        @NotBlank String tipo_serie,

        // Siempre obligatoria y debe ser mayor que 0
        @NotNull @Min(1) Integer cantidad,

        // Requerido solo si es CON_SERIE
        List<Long> series,

        @NotBlank String motivo,
        String observaciones,

        @NotNull Long id_usuario,
        Boolean reposicionAplicada

) {}

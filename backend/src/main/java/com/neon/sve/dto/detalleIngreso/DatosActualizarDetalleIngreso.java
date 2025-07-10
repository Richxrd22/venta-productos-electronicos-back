package com.neon.sve.dto.detalleIngreso;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosActualizarDetalleIngreso(

                @NotNull Long id_detalle,
                @NotNull @Min(0) int cantidad,
                @NotNull @Positive BigDecimal precio_unitario,
                String tipo_serie, // "CON_SERIE", "SIN_SERIE"
                List<String> series 

) {

}

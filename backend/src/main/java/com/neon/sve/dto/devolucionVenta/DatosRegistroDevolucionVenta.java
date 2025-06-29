package com.neon.sve.dto.devolucionVenta;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroDevolucionVenta(

        @NotNull @Min(1) int cantidad,
        String motivo,
        @NotNull Long id_usuario,
        @NotNull Long id_detalle_venta

) {

}

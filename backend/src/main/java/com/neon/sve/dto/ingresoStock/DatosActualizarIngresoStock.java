package com.neon.sve.dto.ingresoStock;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarIngresoStock(

        @NotNull Long id,
        @NotNull Long id_proveedor,
        @NotNull Long id_usuario,
        @NotNull int cantidad) {

}

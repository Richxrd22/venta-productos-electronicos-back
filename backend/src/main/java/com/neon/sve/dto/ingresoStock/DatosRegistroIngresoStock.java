package com.neon.sve.dto.ingresoStock;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroIngresoStock(

                @NotNull Long id_producto,
                @NotNull Long id_proveedor,
                @NotNull Long id_usuario,
                @NotNull int cantidad) {

}

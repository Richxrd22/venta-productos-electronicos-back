package com.neon.sve.dto.devolucionVenta;

import jakarta.validation.constraints.NotNull;

public record DatosProductoDevolucion(

                @NotNull Long id_producto,
                @NotNull int cantidad) {

}

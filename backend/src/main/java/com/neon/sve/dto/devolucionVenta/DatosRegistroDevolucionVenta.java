package com.neon.sve.dto.devolucionVenta;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroDevolucionVenta(

        @NotNull Long id_registro_venta,
        String motivo,
        @NotNull Long id_usuario,
        @NotEmpty List<DatosProductoDevolucion> devolucion // "devolucion" es el nombre de tu array en el JSON

) {

}

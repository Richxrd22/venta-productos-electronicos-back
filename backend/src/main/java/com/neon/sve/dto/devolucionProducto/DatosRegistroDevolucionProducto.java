package com.neon.sve.dto.devolucionProducto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroDevolucionProducto(

    Long id_serie_producto, // Puede ser nulo
    Long id_detalle_ingreso, // Puede ser nulo
    @NotNull int cantidad,
    @NotBlank String motivo,
    String observaciones,
    @NotNull Long id_usuario,
    Boolean reposicionAplicada

) {
    
}

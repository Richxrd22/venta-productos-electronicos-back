package com.neon.sve.dto.detalleVentaSerie;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroDetalleVentaSerie(

    @NotNull Long id_detalleVenta,
    @NotNull Long id_serieProducto

) {
    
}

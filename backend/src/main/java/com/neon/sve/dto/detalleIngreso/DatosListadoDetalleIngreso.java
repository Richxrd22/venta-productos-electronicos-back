package com.neon.sve.dto.detalleIngreso;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.neon.sve.model.stock.DetalleIngreso;
import com.neon.sve.model.stock.SerieProducto;

public record DatosListadoDetalleIngreso(
        Long id,
        String nombre_producto,
        String modelo,
        String codigo_detalle,
        int cantidad,
        BigDecimal precioUnitario,
        BigDecimal subtotal,
        String tipo_serie,
        List<String> series_individuales
) {

    public DatosListadoDetalleIngreso(DetalleIngreso detalle) {
        this(
                detalle.getId(),
                detalle.getId_producto().getNombre(),
                detalle.getId_producto().getModelo(),
                detalle.getCodigo_detalle(),
                detalle.getCantidad(),
                detalle.getPrecio_unitario(),
                detalle.getPrecio_unitario().multiply(BigDecimal.valueOf(detalle.getCantidad())),
                (detalle.getSeriesProductos() != null && !detalle.getSeriesProductos().isEmpty()) ? "CON_SERIE" : "SIN_SERIE",
                (detalle.getSeriesProductos() != null) ?
                        detalle.getSeriesProductos().stream()
                                .map(SerieProducto::getNumeroSerie)
                                .collect(Collectors.toList())
                        : List.of()
        );
    }
}

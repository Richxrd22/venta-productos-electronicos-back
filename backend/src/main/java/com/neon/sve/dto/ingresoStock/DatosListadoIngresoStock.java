package com.neon.sve.dto.ingresoStock;

import com.neon.sve.model.stock.IngresoStock;

public record DatosListadoIngresoStock(

        Long id,
        String producto,
        String proveedor,
        String lote,
        String tipo_documento,
        String numero_documento,
        String observaciones,
        int activo) {

    public DatosListadoIngresoStock(IngresoStock ingresoStock) {
        this(
                ingresoStock.getId(),
                ingresoStock.getDetallesIngreso().getId_producto().getNombre(),
                ingresoStock.getId_proveedor().getRazon_social(),
                ingresoStock.getDetallesIngreso().getCodigoLote(),
                ingresoStock.getTipo_documento(),
                ingresoStock.getNumero_documento(),
                ingresoStock.getObservaciones(),
                ingresoStock.getActivo() != null && ingresoStock.getActivo() ? 1 : 0);
    }
}

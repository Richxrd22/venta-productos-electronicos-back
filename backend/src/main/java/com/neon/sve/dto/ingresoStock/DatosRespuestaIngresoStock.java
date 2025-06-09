package com.neon.sve.dto.ingresoStock;

import java.sql.Timestamp;

import com.neon.sve.model.stock.IngresoStock;

public record DatosRespuestaIngresoStock(

        Long id,
        String producto,
        String proveedor,
        Timestamp fecha_ingreso,
        String lote,
        int cantidad,
        String tipo_documento,
        String numero_documento,
        String observaciones

) {

    public DatosRespuestaIngresoStock(IngresoStock ingresoStock) {
        this(
                ingresoStock.getId(),
                ingresoStock.getDetallesIngreso().getId_producto().getNombre(),
                ingresoStock.getId_proveedor().getRazon_social(),
                ingresoStock.getFecha_ingreso(),
                ingresoStock.getDetallesIngreso().getCodigoLote(),
                ingresoStock.getDetallesIngreso().getCantidad(),
                ingresoStock.getTipo_documento(),
                ingresoStock.getNumero_documento(),
                ingresoStock.getObservaciones());
    }

}

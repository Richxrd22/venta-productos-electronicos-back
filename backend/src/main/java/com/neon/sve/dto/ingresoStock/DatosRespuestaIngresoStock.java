package com.neon.sve.dto.ingresoStock;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.neon.sve.model.stock.IngresoStock;

public record DatosRespuestaIngresoStock(

        Long id,
        Long id_proveedor,
        String proveedor,
        Timestamp fecha_ingreso,
        String tipo_documento,
        String numero_documento,
        String observaciones

) {

    public DatosRespuestaIngresoStock(IngresoStock ingresoStock) {
        this(
                ingresoStock.getId(),
                ingresoStock.getId_proveedor().getId(),
                ingresoStock.getId_proveedor().getRazon_social(),
                ingresoStock.getFecha_ingreso(),
                ingresoStock.getTipo_documento(),
                ingresoStock.getNumero_documento(),
                ingresoStock.getObservaciones());
    }

}

package com.neon.sve.dto.ingresoStock;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neon.sve.model.stock.IngresoStock;

public record DatosListadoIngresoStock(

        Long id,
        String proveedor,
        String codigo_ingreso,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") Timestamp fecha_ingreso,
        String tipo_documento,
        String numero_documento,
        String observaciones,
        BigDecimal total,
        int activo) {

    public DatosListadoIngresoStock(IngresoStock ingresoStock) {
        this(
                ingresoStock.getId(),
                ingresoStock.getId_proveedor().getRazon_social(),
                ingresoStock.getCodigo_ingreso(),
                ingresoStock.getFecha_ingreso(),
                ingresoStock.getTipo_documento(),
                ingresoStock.getNumero_documento(),
                ingresoStock.getObservaciones(),
                ingresoStock.getTotal(),
                ingresoStock.getActivo() != null && ingresoStock.getActivo() ? 1 : 0);
    }
}

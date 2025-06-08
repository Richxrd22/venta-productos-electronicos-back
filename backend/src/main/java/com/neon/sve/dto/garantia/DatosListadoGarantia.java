package com.neon.sve.dto.garantia;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.neon.sve.model.ventas.Garantia;

public record DatosListadoGarantia(

        Long id,
        String producto,
        int cantidad,
        double total,
        Timestamp fechaCreacion,
        LocalDate inicioGarantia,
        LocalDate finGarantia,
        int activo) {
    public DatosListadoGarantia(Garantia garantia) {
        this(
                garantia.getId(),
                garantia.getId_detalle_venta().getId_producto().getNombre(),
                garantia.getId_detalle_venta().getCantidad(),
                garantia.getId_detalle_venta().getTotal(),
                garantia.getId_detalle_venta().getFechaCreacion(),
                garantia.getInicioGarantia(),
                garantia.getFinGarantia(),
                garantia.getActivo() != null && garantia.getActivo() ? 1 : 0);
    }

}

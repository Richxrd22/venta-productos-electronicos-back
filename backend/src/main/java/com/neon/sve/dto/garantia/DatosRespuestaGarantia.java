package com.neon.sve.dto.garantia;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.neon.sve.model.ventas.Garantia;

public record DatosRespuestaGarantia(

        Long id_garantia,
        Long id_detalle_venta,
        String producto,
        int cantidad,
        Timestamp fecha_creacion,
        LocalDate inicio_garantia,
        LocalDate fin_garantia,
        int activo) {
    public DatosRespuestaGarantia(Garantia garantia) {
        this(
                garantia.getId(),
                garantia.getId_detalle_venta().getId(),
                garantia.getId_detalle_venta().getId_producto().getNombre(),
                garantia.getId_detalle_venta().getCantidad(),
                garantia.getId_detalle_venta().getFecha_creacion(),
                garantia.getInicioGarantia(),
                garantia.getFinGarantia(),
                garantia.getActivo() != null && garantia.getActivo() ? 1 : 0);
    }

}

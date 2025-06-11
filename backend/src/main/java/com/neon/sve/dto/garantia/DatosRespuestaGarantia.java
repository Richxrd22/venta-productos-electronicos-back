package com.neon.sve.dto.garantia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import com.neon.sve.model.ventas.Garantia;

public record DatosRespuestaGarantia(

        Long id,
        String producto,
        int cantidad,
        BigDecimal total,
        Timestamp fechaCreacion,
        LocalDate inicioGarantia,
        LocalDate finGarantia,
        int activo) {
    public DatosRespuestaGarantia(Garantia garantia) {
        this(
                garantia.getId(),
                garantia.getId_detalle_venta().getId_producto().getNombre(),
                garantia.getId_detalle_venta().getCantidad(),
                garantia.getId_detalle_venta().getTotal(),
                garantia.getId_detalle_venta().getFecha_creacion(),
                garantia.getInicioGarantia(),
                garantia.getFinGarantia(),
                garantia.getActivo() != null && garantia.getActivo() ? 1 : 0);
    }

}

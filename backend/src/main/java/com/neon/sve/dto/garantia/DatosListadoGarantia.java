package com.neon.sve.dto.garantia;

import java.time.LocalDate;

import com.neon.sve.model.ventas.Garantia;

public record DatosListadoGarantia(

        Long id_garantia,
        String producto,
        int cantidad,
        LocalDate inicioGarantia,
        LocalDate finGarantia,
        int activo) {
    public DatosListadoGarantia(Garantia garantia) {
        this(
                garantia.getId(),
                garantia.getId_detalle_venta().getId_producto().getNombre(),
                garantia.getId_detalle_venta().getCantidad(),
                garantia.getInicioGarantia(),
                garantia.getFinGarantia(),
                garantia.getActivo() != null && garantia.getActivo() ? 1 : 0);
    }

}

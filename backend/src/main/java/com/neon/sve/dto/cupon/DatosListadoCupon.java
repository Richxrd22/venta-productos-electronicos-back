package com.neon.sve.dto.cupon;

import java.time.LocalDate;

import com.neon.sve.model.ventas.Cupon;

public record DatosListadoCupon(

        Long id,
        String codigo,
        String descripcion,
        String tipoDescuento,
        Double descuentoPorcentaje,
        Double descuentoMonto,
        LocalDate fechaFin,
        int usosActuales,
        int maxUsos,
        int activo) {
    public DatosListadoCupon(Cupon cupon) {
        this(
                cupon.getId(),
                cupon.getCodigo(),
                cupon.getDescripcion(),
                cupon.getTipoDescuentoCupon().name(),
                cupon.getDescuentoPorcentaje(),
                cupon.getDescuentoMonto(),
                cupon.getFechaFin(),
                cupon.getUsosActuales(),
                cupon.getMaxUsos(),
                cupon.getActivo() != null && cupon.getActivo() ? 1 : 0);

    }
}

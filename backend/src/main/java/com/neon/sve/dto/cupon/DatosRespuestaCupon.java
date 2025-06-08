package com.neon.sve.dto.cupon;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.neon.sve.model.ventas.Cupon;

public record DatosRespuestaCupon(
        Long id,
        String codigo,
        String descripcion,
        String tipoDescuento,
        Double descuentoPorcentaje,
        Double descuentoMonto,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        int maxUsos,
        int usosActuales,
        Timestamp fechaCreacion,
        int activo) {
    public DatosRespuestaCupon(Cupon cupon) {
        this(
                cupon.getId(),
                cupon.getCodigo(),
                cupon.getDescripcion(),
                cupon.getTipoDescuentoCupon().name(),
                cupon.getDescuentoPorcentaje(),
                cupon.getDescuentoMonto(),
                cupon.getFechaInicio(),
                cupon.getFechaFin(),
                cupon.getMaxUsos(),
                cupon.getUsosActuales(),
                cupon.getFechaCreacion(),
                cupon.getActivo() != null && cupon.getActivo() ? 1 : 0);
    }
}

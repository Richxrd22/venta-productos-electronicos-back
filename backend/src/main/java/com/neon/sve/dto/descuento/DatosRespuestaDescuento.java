package com.neon.sve.dto.descuento;

import java.time.LocalDate;

import com.neon.sve.model.ventas.Descuento;

public record DatosRespuestaDescuento(

        Long id,
        Long id_categoria,
        String nombreCategoria,
        double porcentaje,
        LocalDate fecha_inicio,
        LocalDate fecha_fin,
        int activo

) {

    public DatosRespuestaDescuento(Descuento descuento) {
        this(
                descuento.getId(),
                descuento.getCategoria().getId(),
                descuento.getCategoria().getNombre(),
                descuento.getPorcentaje(),
                descuento.getFecha_inicio(),
                descuento.getFechaFin(),
                descuento.getActivo() != null && descuento.getActivo() ? 1 : 0);
    }

}

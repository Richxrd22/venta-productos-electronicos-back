package com.neon.sve.dto.descuento;

import java.time.LocalDate;

import com.neon.sve.model.ventas.Descuento;

public record DatosRespuestaDescuento(

        Long id,
        Long id_categoria,
        String nombreCategoria,
        double porcentaje,
        LocalDate fechaInicio,
        LocalDate fechaFinal,
        int activo

) {

    public DatosRespuestaDescuento(Descuento descuento) {
        this(
                descuento.getId(),
                descuento.getId_categoria().getId(),
                descuento.getId_categoria().getNombre(),
                descuento.getPorcentaje(),
                descuento.getFecha_inicio(),
                descuento.getFecha_fin(),
                descuento.getActivo() != null && descuento.getActivo() ? 1 : 0);
    }

}

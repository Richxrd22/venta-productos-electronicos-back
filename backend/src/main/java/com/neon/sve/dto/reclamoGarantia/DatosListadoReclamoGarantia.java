package com.neon.sve.dto.reclamoGarantia;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.neon.sve.model.ventas.ReclamoGarantia;

public record DatosListadoReclamoGarantia(

        Long id,
        LocalDate inicioGarantia,
        LocalDate finalGarantia,
        String descripcion,
        String estado,
        int activo,
        Timestamp fechaReclamo) {
    public DatosListadoReclamoGarantia(ReclamoGarantia reclamoGarantia) {
        this(
                reclamoGarantia.getId(),
                reclamoGarantia.getId_garantia().getInicioGarantia(),
                reclamoGarantia.getId_garantia().getFinGarantia(),
                reclamoGarantia.getDescripcion(),
                reclamoGarantia.getEstado().name(),
                reclamoGarantia.getActivo() != null && reclamoGarantia.getActivo() ? 1 : 0,
                reclamoGarantia.getFechaReclamo());
    }

}

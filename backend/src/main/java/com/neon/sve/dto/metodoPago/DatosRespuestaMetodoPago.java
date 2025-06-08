package com.neon.sve.dto.metodoPago;

import com.neon.sve.model.ventas.MetodoPago;

public record DatosRespuestaMetodoPago(

        Long id,
        String metodo,
        int activo) {
    public DatosRespuestaMetodoPago(MetodoPago metodoPago) {
        this(
                metodoPago.getId(),
                metodoPago.getMetodo(),
                metodoPago.getActivo() != null && metodoPago.getActivo() ? 1 : 0);
    }

}

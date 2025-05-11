package com.neon.sve.dto.marca;

import com.neon.sve.model.producto.Marca;

public record DatosRespuestaMarca(
        Long id,
        String nombre,
        int activo) {

    public DatosRespuestaMarca(Marca marca) {
        this(
                marca.getId(),
                marca.getNombre(),
                marca.getActivo() != null && marca.getActivo() ? 1 : 0);
    }

}

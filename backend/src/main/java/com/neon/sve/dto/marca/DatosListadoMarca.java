package com.neon.sve.dto.marca;

import com.neon.sve.model.producto.Marca;

public record DatosListadoMarca(

        Long id,
        String nombre,
        int activo) {

    public DatosListadoMarca(Marca marca) {
        this(
                marca.getId(),
                marca.getNombre(),
                marca.getActivo() != null && marca.getActivo() ? 1 : 0);
    }

}

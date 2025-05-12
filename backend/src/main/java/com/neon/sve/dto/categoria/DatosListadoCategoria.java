package com.neon.sve.dto.categoria;

import com.neon.sve.model.producto.Categoria;

public record DatosListadoCategoria(

        Long id,
        String nombre,
        int activo) {

    public DatosListadoCategoria(Categoria categoria) {
        this(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getActivo() != null && categoria.getActivo() ? 1 : 0);
    }

}

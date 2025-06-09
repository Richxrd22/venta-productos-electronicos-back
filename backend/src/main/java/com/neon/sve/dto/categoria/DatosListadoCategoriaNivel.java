package com.neon.sve.dto.categoria;

import com.neon.sve.model.producto.Categoria;

public record DatosListadoCategoriaNivel(
        Long id,
        String nombre,
        Long id_padre,
        int activo) {

    public DatosListadoCategoriaNivel(Categoria categoria) {
        this(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getId_categoria_padre(),
                categoria.getActivo() != null && categoria.getActivo() ? 1 : 0);
    }

}

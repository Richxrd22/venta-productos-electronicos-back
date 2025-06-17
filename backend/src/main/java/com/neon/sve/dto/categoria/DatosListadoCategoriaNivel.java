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
                categoria.getCategoriaPadre() != null ? categoria.getCategoriaPadre().getId() : null,
                categoria.getActivo() != null && categoria.getActivo() ? 1 : 0);
    }

}

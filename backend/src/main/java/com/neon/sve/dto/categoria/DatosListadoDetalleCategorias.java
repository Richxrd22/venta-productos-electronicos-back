package com.neon.sve.dto.categoria;

import java.util.List;
import java.util.stream.Collectors;

import com.neon.sve.model.producto.Categoria;

public record DatosListadoDetalleCategorias(
        Long id,
        String nombre,
        Long id_padre,
        int nivel,
        int activo,
        List<DatosListadoCategoria> subCategorias

) {

    public DatosListadoDetalleCategorias(Categoria categoria) {
        this(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getCategoriaPadre() != null ? categoria.getCategoriaPadre().getId() : null,
                categoria.getNivel(),
                categoria.getActivo() != null && categoria.getActivo() ? 1 : 0, // Si la entidad 'categoria' tiene
                                                                                // subcategorías, las convierte a DTOs.
                categoria.getSubcategorias() != null && !categoria.getSubcategorias().isEmpty()
                        ? categoria.getSubcategorias().stream() // Convierte la lista de entidades Categoria en un
                                                                // 'Stream'
                                .map(DatosListadoCategoria::new) // Por cada Categoria en el Stream, llama al
                                                                 // constructor de DatosListadoCategoria.
                                .collect(Collectors.toList()) // Recolecta todos los DTOs resultantes en una nueva
                                                              // lista.
                        : List.of() // Si no hay subcategorías (es una hoja del árbol), devuelve una lista vacía.
        );

    }
}

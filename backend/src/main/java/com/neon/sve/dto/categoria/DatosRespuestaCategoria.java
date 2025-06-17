package com.neon.sve.dto.categoria;

import com.neon.sve.model.producto.Categoria;

public record DatosRespuestaCategoria(

                Long id,
                String nombre,
                Long id_padre,
                int nivel,
                int activo

) {

        public DatosRespuestaCategoria(Categoria categoria) {
                this(
                        categoria.getId(),
                        categoria.getNombre(),
                        categoria.getCategoriaPadre() != null ? categoria.getCategoriaPadre().getId() : null,
                        categoria.getNivel(),
                        categoria.getActivo() != null && categoria.getActivo() ? 1 : 0);
        }

}

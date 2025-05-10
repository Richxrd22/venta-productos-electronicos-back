package com.neon.sve.dto.categoria;

import com.neon.sve.model.Producto.Categoria;

public record DatosRespuestaCategoria(

                Long id,
                String nombre,
                int activo

) {

        public DatosRespuestaCategoria(Categoria categoria) {
                this(
                                categoria.getId(),
                                categoria.getNombre(),
                                categoria.getActivo() != null && categoria.getActivo() ? 1 : 0);
        }

}

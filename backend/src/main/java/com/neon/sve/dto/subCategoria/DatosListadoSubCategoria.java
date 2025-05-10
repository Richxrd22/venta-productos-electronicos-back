package com.neon.sve.dto.subCategoria;

import com.neon.sve.model.Producto.SubCategoria;

public record DatosListadoSubCategoria(

        Long id,
        String nombre,
        int activo,
        String nombre_categoria) {

    public DatosListadoSubCategoria(SubCategoria subCategoria) {
        this(
                subCategoria.getId(),
                subCategoria.getNombre(),
                subCategoria.getActivo() != null && subCategoria.getActivo() ? 1 : 0,
                subCategoria.getId_categoria().getNombre());
    }

}

package com.neon.sve.dto.subCategoria;

import com.neon.sve.model.producto.SubCategoria;

public record DatosRespuestaSubCategoria(

        Long id,
        String nombre,
        int activo,
        String nombre_categoria) {

    public DatosRespuestaSubCategoria(SubCategoria subCategoria) {
        this(
                subCategoria.getId(),
                subCategoria.getNombre(),
                subCategoria.getActivo() != null && subCategoria.getActivo() ? 1 : 0,
                subCategoria.getId_categoria().getNombre());
    }

}

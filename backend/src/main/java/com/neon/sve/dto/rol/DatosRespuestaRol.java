package com.neon.sve.dto.rol;

import com.neon.sve.model.Usuario.Rol;

public record DatosRespuestaRol(
        Long id_rol,
        String nombre) {

    public DatosRespuestaRol(Rol rol) {
        this(
                rol.getId_rol(),
                rol.getNombre());
    }
}
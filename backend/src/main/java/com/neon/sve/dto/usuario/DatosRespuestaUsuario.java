package com.neon.sve.dto.usuario;

import com.neon.sve.model.Usuario.Usuario;

public record DatosRespuestaUsuario(
        Long id_usuario,
        String correo,
        String nombre_empleado,
        String nombre_rol) {

    public DatosRespuestaUsuario(Usuario usuario) {
        this(
                usuario.getId_usuario(),
                usuario.getCorreo(),
                usuario.getId_empleado().getNombre() + " " + usuario.getId_empleado().getApellido(),
                usuario.getId_rol().getNombre());
    }
}
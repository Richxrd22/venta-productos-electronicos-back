package com.neon.sve.dto.usuario;

import com.neon.sve.model.usuario.Usuario;

public record DatosListadoUsuario(
        Long id,
        String correo,
        String nombre_empleado,
        String nombre_rol,
        int activo) {

    public DatosListadoUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getCorreo(),
                usuario.getId_empleado().getNombre() + " " + usuario.getId_empleado().getApellido(),
                usuario.getId_rol().getNombre(),
                usuario.getActivo() != null && usuario.getActivo() ? 1 : 0 // Convertir a 0 o 1
                );
    }
}
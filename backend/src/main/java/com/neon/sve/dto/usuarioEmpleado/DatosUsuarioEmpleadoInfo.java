package com.neon.sve.dto.usuarioEmpleado;

public record DatosUsuarioEmpleadoInfo(
        String nombre,
        String apellido,
        String correo,
        String rol,
        Boolean clave_cambiada,
        Boolean activo) {
            

}

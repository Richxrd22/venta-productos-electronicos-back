package com.neon.sve.dto.usuarioempleado;

public record DatosUsuarioEmpleadoInfo(
        String nombre,
        String apellido,
        String correo,
        String rol,
        Boolean clave_cambiada,
        Boolean activo) {
            

}

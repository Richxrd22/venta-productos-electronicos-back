package com.neon.sve.dto.usuarioEmpleado;

public record DatosUsuarioEmpleadoInfo(
                Long id,
                String nombre,
                String apellido,
                String correo,
                String rol,
                Boolean clave_cambiada,
                Boolean activo) {

}

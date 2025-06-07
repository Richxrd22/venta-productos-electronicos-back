package com.neon.sve.dto.usuarioEmpleado;

import com.neon.sve.model.usuario.Empleado;

public record DatosRespuestaUsuarioEmpleado(
    Long id_empleado,
    String nombre_apellido,
    String dni,
    String correo,
    String celular,
    int activo,
    String nombre_rol
) {
    public DatosRespuestaUsuarioEmpleado(Empleado empleado) {
        this(
            empleado.getId(),
            empleado.getNombre() + " " + empleado.getApellido(),
            empleado.getDni(),
            empleado.getUsuario().getCorreo(),
            empleado.getCelular(),
            empleado.getActivo() != null && empleado.getActivo() ? 1 : 0,
            empleado.getUsuario().getId_rol().getNombre());
    }
}

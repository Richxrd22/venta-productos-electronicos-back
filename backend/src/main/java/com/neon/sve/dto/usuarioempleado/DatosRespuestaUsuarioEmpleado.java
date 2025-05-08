package com.neon.sve.dto.usuarioempleado;

import com.neon.sve.model.Usuario.Empleado;

public record DatosRespuestaUsuarioEmpleado(
    Long id_empleado,
    String nombre_apellido,
    String dni,
    String correo_empleado,
    String correo_empresarial,
    String celular,
    int estado,
    String nombre_rol
) {
    public DatosRespuestaUsuarioEmpleado(Empleado empleado) {
        this(
            empleado.getId_empleado(),
            empleado.getNombre() + " " + empleado.getApellido(),
            empleado.getDni(),
            empleado.getCorreo(),
            empleado.getUsuario().getCorreo(),
            empleado.getCelular(),
            empleado.getEstado() != null && empleado.getEstado() ? 1 : 0,
            empleado.getUsuario().getId_rol().getNombre());
    }
}

package com.neon.sve.dto.empleado;

import com.neon.sve.model.usuario.Empleado;

public record DatosRespuestaEmpleado(
        Long id,
        String nombre,
        String apellido,
        String dni,
        String correo,
        String celular,
        int activo, 
        Long id_rol) {

    public DatosRespuestaEmpleado(Empleado empleado) {
        this(
            empleado.getId(),
            empleado.getNombre(),
            empleado.getApellido(),
            empleado.getDni(),
            empleado.getUsuario().getCorreo(),
            empleado.getCelular(),
            empleado.getActivo() != null && empleado.getActivo() ? 1 : 0,
            empleado.getUsuario().getId_rol().getId()
        );
    }
}

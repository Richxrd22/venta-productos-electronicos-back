package com.neon.sve.dto.empleado;

import com.neon.sve.model.usuario.Empleado;

public record DatosListadoEmpleado(
        Long id,
        String nombre,
        String apellido,
        String dni,
        String correo,
        String celular,
        int activo
        ) {

    public DatosListadoEmpleado(Empleado empleado) {
        this(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getDni(),
                empleado.getUsuario().getCorreo(),
                empleado.getCelular(),
                empleado.getActivo() != null && empleado.getActivo() ? 1 : 0); // Convertir a 0 o 1,
    }
}
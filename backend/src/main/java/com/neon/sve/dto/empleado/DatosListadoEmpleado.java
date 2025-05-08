package com.neon.sve.dto.empleado;

import com.neon.sve.model.Usuario.Empleado;

public record DatosListadoEmpleado(
        Long id_empleado,
        String nombre,
        String apellido,
        String dni,
        String correo,
        String celular,
        int estado
        ) {

    public DatosListadoEmpleado(Empleado empleado) {
        this(
                empleado.getId_empleado(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getDni(),
                empleado.getCorreo(),
                empleado.getCelular(),
                empleado.getEstado() != null && empleado.getEstado() ? 1 : 0); // Convertir a 0 o 1,
    }
}
package com.neon.sve.dto.empleado;

import com.neon.sve.model.Usuario.Empleado;

public record DatosRespuestaEmpleado(
        Long id_empleado,
        String nombre,
        String apellido,
        String dni,
        String correo_empleado,
        String celular,
        int activo) {

    public DatosRespuestaEmpleado(Empleado empleado) {
        this(
                empleado.getId_empleado(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getDni(),
                empleado.getCorreo_empleado(),
                empleado.getCelular(),
                empleado.getActivo() != null && empleado.getActivo() ? 1 : 0  // Convertir a 0 o 1
                );
    }
}
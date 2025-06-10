package com.neon.sve.dto.cliente;

import java.sql.Timestamp;

import com.neon.sve.model.ventas.Cliente;

public record DatosRespuestaCliente(
        Long id,
        String nombre,
        String apellido,
        String dni,
        String celular,
        String correo,
        Timestamp fecha_creacion,
        int activo) {
    public DatosRespuestaCliente(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDni(),
                cliente.getCelular(),
                cliente.getCorreo(),
                cliente.getFecha_creacion(),
                cliente.getActivo() != null && cliente.getActivo() ? 1 : 0);
    }

}

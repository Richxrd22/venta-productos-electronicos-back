package com.neon.sve.dto.cliente;

import com.neon.sve.model.ventas.Cliente;

public record DatosListadoCliente(
        Long id,
        String nombre,
        String apellido,
        String dni,
        String celular,
        String correo,
        int activo
) {
    public DatosListadoCliente(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDni(),
                cliente.getCelular(),
                cliente.getCorreo(),
                cliente.getActivo() != null && cliente.getActivo() ? 1 : 0
        );
    }
    
}

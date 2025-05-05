package com.neon.sve.dto.Proveedores;

import com.neon.sve.model.Producto.Proveedor;

public record DatosRespuestaProveedores(

        Long id_proveedor,
        String nombre_apellido,
        String correo_empleado,
        String dni,
        String celular_telefono,
        int activo,
        String nombre_empresa

) {

    public DatosRespuestaProveedores(Proveedor proveedor) {

        this(
                proveedor.getId_proveedor(),
                proveedor.getNombre() + " " + proveedor.getApellido(),
                proveedor.getCorreo(),
                proveedor.getDni(),
                proveedor.getCelular() + " y/o " + proveedor.getTelefono(),
                
                proveedor.getActivo() != null && proveedor.getActivo() ? 1 : 0,
                proveedor.getId_empresa().getNombre());

    }

}

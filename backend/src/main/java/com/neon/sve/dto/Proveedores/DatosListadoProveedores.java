package com.neon.sve.dto.Proveedores;

import com.neon.sve.model.Producto.Empresa;
import com.neon.sve.model.Producto.Proveedor;

public record DatosListadoProveedores(

        Long id_proveedor,
        String nombre,
        String apellido,
        String correo_empleado,
        String dni,
        String celular,
        int activo,
        Empresa id_empresa,
        String telefono

) {

    public DatosListadoProveedores(Proveedor proveedor) {
        this(
                proveedor.getId_proveedor(),
                proveedor.getNombre(),
                proveedor.getApellido(),
                proveedor.getCorreo(),
                proveedor.getDni(),
                proveedor.getCelular(),
                proveedor.getActivo() != null && proveedor.getActivo() ? 1 : 0,
                proveedor.getId_empresa(),
                proveedor.getTelefono());
    }

}

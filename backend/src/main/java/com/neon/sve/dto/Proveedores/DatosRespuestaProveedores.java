package com.neon.sve.dto.Proveedores;

import com.neon.sve.model.Producto.Proveedor;

public record DatosRespuestaProveedores(

        Long id,
        String razon_social,
        String ruc,
        String correo,
        String direccion,
        String celular,
        String telefono,
        int activo

) {

    public DatosRespuestaProveedores(Proveedor proveedor) {

        this(
                proveedor.getId(),
                proveedor.getRazon_social(),
                  proveedor.getRuc(),
                proveedor.getCorreo(),
                proveedor.getDireccion(),
                proveedor.getCelular(),
                proveedor.getTelefono(),                
                proveedor.getActivo() != null && proveedor.getActivo() ? 1 : 0);

    }

}

package com.neon.sve.dto.producto;

import java.sql.Timestamp;

import com.neon.sve.model.producto.Producto;

public record DatosListadoProducto(

        Long id,
        String sku,
        String nombre,
        String descripcion,
        Double precio_venta,
        Double precio_compra,
        int min_stock,
        int max_stock,
        int garantia_meses,
        int activo,
        Timestamp fecha_creacion,
        String correo_usuario,
        String nombre_usuario,
        String nombre_subcategoria,
        String nombre_categoria,
        String nombre_marca

) {

    public DatosListadoProducto(Producto producto) {
        this(
                producto.getId(),
                producto.getSku(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio_venta(),
                producto.getPrecio_compra(),
                producto.getMin_stock(),
                producto.getMax_stock(),
                producto.getGarantia_meses(),
                producto.getActivo() != null && producto.getActivo() ? 1 : 0,
                producto.getFecha_creacion(),
                producto.getId_usuario().getCorreo(),
                producto.getId_usuario().getId_empleado().getNombre(),
                producto.getId_subcategoria().getNombre(),
                producto.getId_subcategoria().getId_categoria().getNombre(),
                producto.getId_marca().getNombre());
    }

}

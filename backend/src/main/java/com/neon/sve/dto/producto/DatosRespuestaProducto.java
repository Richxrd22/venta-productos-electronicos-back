package com.neon.sve.dto.producto;

import java.sql.Timestamp;

import com.neon.sve.model.producto.Producto;

public record DatosRespuestaProducto(

        Long id,
        String sku,
        String nombre,
        String modelo,
        String color,
        String descripcion,
        Double precio_venta,
        int min_stock,
        int max_stock,
        int garantia_meses,
        int activo,
        Timestamp fecha_creacion,
        String nombre_usuario,
        Long id_categoria,
        Long id_marca) {

    public DatosRespuestaProducto(Producto producto) {
        this(
                producto.getId(),
                producto.getSku(),
                producto.getNombre(),
                producto.getModelo(),
                producto.getColor(),
                producto.getDescripcion(),
                producto.getPrecio_venta(),
                producto.getMin_stock(),
                producto.getMax_stock(),
                producto.getGarantia_meses(),
                producto.getActivo() != null && producto.getActivo() ? 1 : 0,
                producto.getFecha_creacion(),
                producto.getId_usuario().getId_empleado().getNombre() + " "
                        + producto.getId_usuario().getId_empleado().getApellido(),
                producto.getId_categoria().getId(),
                producto.getId_marca().getId());
    }
}

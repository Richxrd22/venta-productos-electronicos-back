package com.neon.sve.dto.producto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.neon.sve.model.producto.Producto;

public record DatosListadoProducto(

                Long id,
                String sku,
                String nombre,
                String modelo,
                String color,
                String descripcion,
                BigDecimal precio_venta,
                int min_stock,
                int max_stock,
                int stock_actual,
                int garantia_meses,
                int activo,
                Timestamp fecha_creacion,
                String nombre_usuario,
                String nombre_categoria,
                String nombre_marca

) {

        public DatosListadoProducto(Producto producto) {
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
                                producto.getStock_actual(),
                                producto.getGarantia_meses(),
                                producto.getActivo() != null && producto.getActivo() ? 1 : 0,
                                producto.getFecha_creacion(),
                                producto.getId_usuario().getId_empleado().getNombre() + " "
                                                + producto.getId_usuario().getId_empleado().getApellido(),
                                producto.getId_categoria().getNombre(),
                                producto.getId_marca().getNombre());
        }

}
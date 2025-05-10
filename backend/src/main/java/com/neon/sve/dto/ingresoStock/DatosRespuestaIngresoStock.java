package com.neon.sve.dto.ingresoStock;

import java.sql.Timestamp;

import com.neon.sve.model.Ventas.IngresoStock;

public record DatosRespuestaIngresoStock(

        Long id,
        String nombre_producto,
        int min_stock,
        int max_stock,
        String razon_proveedor,
        String ruc_proveedor,
        String celular_proveedor,
        String correo_usuario,
        String nombre_usuario,
        String sku,
        int cantidad,
        Timestamp fecha_ingreso,
        int activo

) {

    public DatosRespuestaIngresoStock(IngresoStock ingresoStock) {
        this(
                ingresoStock.getId(),
                ingresoStock.getId_producto().getNombre(),
                ingresoStock.getId_producto().getMin_stock(),
                ingresoStock.getId_producto().getMax_stock(),
                ingresoStock.getId_proveedor().getRazon_social(),
                ingresoStock.getId_proveedor().getRuc(),
                ingresoStock.getId_proveedor().getCelular(),
                ingresoStock.getId_usuario().getCorreo(),
                ingresoStock.getId_usuario().getId_empleado().getNombre(),
                ingresoStock.getSku(),
                ingresoStock.getCantidad(),
                ingresoStock.getFecha_ingreso(),
                ingresoStock.getActivo() != null && ingresoStock.getActivo() ? 1 : 0);
    }

}

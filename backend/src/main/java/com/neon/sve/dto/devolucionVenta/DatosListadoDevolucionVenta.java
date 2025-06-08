package com.neon.sve.dto.devolucionVenta;

import java.sql.Timestamp;

import com.neon.sve.model.ventas.DevolucionVenta;

public record DatosListadoDevolucionVenta(

        Long id,
        double totalVenta,
        Timestamp fecha,
        int cantidad,
        String motivo,
        String correoUsuario) {
    public DatosListadoDevolucionVenta(DevolucionVenta devolucionVenta) {
        this(
                devolucionVenta.getId(),
                devolucionVenta.getId_detalle_venta().getTotal(),
                devolucionVenta.getFecha(),
                devolucionVenta.getCantidad(),
                devolucionVenta.getMotivo(),
                devolucionVenta.getId_usuario().getCorreo()
        );
    }

}

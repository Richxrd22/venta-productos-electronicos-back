package com.neon.sve.dto.registroVenta;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.neon.sve.model.ventas.RegistroVenta;

public record DatosRespuestaRegistroVenta(
        Long id,
        Timestamp fecha,
        BigDecimal igv_porcentaje,
        BigDecimal subtotal,
        BigDecimal igv_total,
        BigDecimal descuento,
        BigDecimal total,
        Boolean cancelado,
        int activo,
        Long id_usuario,
        String nombreUsuario,
        Long id_cliente,
        String nombreCliente,
        Long id_metodo_pago,
        String nombreMetodoPago,
        Long id_cupon,
        String codigoCupon) {

    public DatosRespuestaRegistroVenta(RegistroVenta registroVenta) {
        this(
                registroVenta.getId(),
                registroVenta.getFecha(),
                registroVenta.getIgv_porcentaje(),
                registroVenta.getSubtotal(),
                registroVenta.getIgv_porcentaje(),
                registroVenta.getDescuento(),
                registroVenta.getTotal(),
                registroVenta.getCancelado(),
                registroVenta.getActivo() != null && registroVenta.getActivo() ? 1 : 0,
                registroVenta.getId_usuario().getId(),
                registroVenta.getId_usuario().getCorreo(),
                registroVenta.getId_cliente().getId(),
                registroVenta.getId_cliente().getNombre() + " " + registroVenta.getId_cliente().getApellido(),
                registroVenta.getId_metodo_pago().getId(),
                registroVenta.getId_metodo_pago().getMetodo(),
                registroVenta.getId_cupon() != null ? registroVenta.getId_cupon().getId() : null,
                registroVenta.getId_cupon() != null ? registroVenta.getId_cupon().getCodigo() : null);
    }
}

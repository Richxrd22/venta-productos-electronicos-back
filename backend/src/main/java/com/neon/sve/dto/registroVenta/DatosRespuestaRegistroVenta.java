package com.neon.sve.dto.registroVenta;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.neon.sve.model.ventas.RegistroVenta;

public record DatosRespuestaRegistroVenta(
        Long id,
        Timestamp fecha,
        BigDecimal igvPorcentaje,
        BigDecimal subtotal,
        BigDecimal igvTotal,
        BigDecimal descuento,
        BigDecimal total,
        Boolean cancelado,
        int activo,
        String nombreUsuario,
        String nombreCliente,
        String nombreMetodoPago,
        String codigoCupon) {
    
    public DatosRespuestaRegistroVenta(RegistroVenta registroVenta) {
        this(
            registroVenta.getId(),
            registroVenta.getFecha(),
            registroVenta.getIgvPorcentaje(),
            registroVenta.getSubtotal(),
            registroVenta.getIgvTotal(),
            registroVenta.getDescuento(),
            registroVenta.getTotal(),
            registroVenta.getCancelado(),
            registroVenta.getActivo() != null && registroVenta.getActivo() ? 1 : 0,
            registroVenta.getId_usuario().getCorreo(),
            registroVenta.getId_cliente().getNombre() + " " + registroVenta.getId_cliente().getApellido(),
            registroVenta.getId_metodo_pago().getMetodo(),
            registroVenta.getId_cupon() != null ? registroVenta.getId_cupon().getCodigo() : null
        );
    }
}

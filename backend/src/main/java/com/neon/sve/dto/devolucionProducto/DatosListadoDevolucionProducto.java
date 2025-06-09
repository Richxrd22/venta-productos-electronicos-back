package com.neon.sve.dto.devolucionProducto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.neon.sve.model.stock.DevolucionProducto;

public record DatosListadoDevolucionProducto(

        Long id,
        String numeroSerieProducto,
        String codigo_lote,
        BigDecimal precio_uni,
        int cantidad,
        Timestamp fechaDevolucion,
        String motivo,
        String observaciones,
        String correoUsuario,
        Boolean reposicionAplicada

) {

    public DatosListadoDevolucionProducto(DevolucionProducto devolucionProducto) {
        this(
                devolucionProducto.getId(),
                devolucionProducto.getId_serie_producto().getNumeroSerie(),
                devolucionProducto.getId_detalle_ingreso().getCodigoLote(),
                devolucionProducto.getId_detalle_ingreso().getPrecio_unitario(),
                devolucionProducto.getCantidad(),
                devolucionProducto.getFechaDevolucion(),
                devolucionProducto.getMotivo(),
                devolucionProducto.getObservaciones(),
                devolucionProducto.getId_usuario().getCorreo(),
                devolucionProducto.getReposicionAplicada());
    }

}

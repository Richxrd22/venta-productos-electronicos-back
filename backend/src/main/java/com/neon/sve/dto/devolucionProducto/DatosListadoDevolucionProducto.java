package com.neon.sve.dto.devolucionProducto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neon.sve.model.stock.DevolucionProducto;

public record DatosListadoDevolucionProducto(

        Long id_devolucion_producto,
        String codigo_lote,
        int cantidad,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") Timestamp fecha_devolucion,
        String motivo,
        String observaciones,
        int reposicion_aplicada,
        int estado

) {

    public DatosListadoDevolucionProducto(DevolucionProducto devolucionProducto) {
        this(
                devolucionProducto.getId(),
                devolucionProducto.getId_detalle_ingreso().getCodigo_detalle(),
                devolucionProducto.getCantidad(),
                devolucionProducto.getFechaDevolucion(),
                devolucionProducto.getMotivo(),
                devolucionProducto.getObservaciones(),
                devolucionProducto.getReposicionAplicada() != null && devolucionProducto.getReposicionAplicada() ? 1
                        : 0,
                devolucionProducto.getActivo() != null && devolucionProducto.getActivo() ? 1 : 0);
    }

}
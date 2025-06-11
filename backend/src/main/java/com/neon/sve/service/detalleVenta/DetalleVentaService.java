package com.neon.sve.service.detalleVenta;

import java.util.List;

import com.neon.sve.dto.detalleVenta.DatosListadoDetalleVenta;
import com.neon.sve.dto.detalleVenta.DatosRegistroDetalleVenta;
import com.neon.sve.dto.detalleVenta.DatosRespuestDetalleVenta;

public interface DetalleVentaService {

    DatosRespuestDetalleVenta guardar(DatosRegistroDetalleVenta datosRegistro);
    List<DatosListadoDetalleVenta> listarPorRegistroVenta(Long idRegistroVenta);
    
}

package com.neon.sve.service.serieProducto;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.serie.DatosActualizarSerie;
import com.neon.sve.dto.serie.DatosListadoSerie;
import com.neon.sve.dto.serie.DatosListadoSerieDetalle;
import com.neon.sve.dto.serie.DatosRegistroSerie;
import com.neon.sve.dto.serie.DatosRespuestaSerie;

public interface SerieProductoService {

    DatosRespuestaSerie getSerieProductoById(Long id);

    Page<DatosListadoSerie> getAllSerieProducto(Pageable pageable);

    List<DatosListadoSerieDetalle> getAllSerieProductoByDetalleIngresoId(Long id_detalle_ingreso);

    DatosRespuestaSerie createSerieProductos(DatosRegistroSerie datosRegistroSerie);

    DatosRespuestaSerie updateSerieProductos(DatosActualizarSerie datosActualizarSerie);

    
} 
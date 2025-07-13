package com.neon.sve.service.categoria;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.categoria.DatosActualizarCategoria;
import com.neon.sve.dto.categoria.DatosListadoCategoria;
import com.neon.sve.dto.categoria.DatosListadoCategoriaNivel;
import com.neon.sve.dto.categoria.DatosListadoDetalleCategorias;
import com.neon.sve.dto.categoria.DatosRegistroCategoria;
import com.neon.sve.dto.categoria.DatosRespuestaCategoria;

public interface CategoriaService {
    
    DatosRespuestaCategoria getCategoriaById(Long id);

    Page<DatosListadoCategoria> getAllCategoria(Pageable pageable);

    Page<DatosListadoDetalleCategorias> getAllCategoriaDetalle(Pageable pageable);

    DatosRespuestaCategoria createCategoria(DatosRegistroCategoria datosRegistroCategoria);

    DatosRespuestaCategoria updateCategoria(DatosActualizarCategoria datosActualizarCategoria);

    void activarCategoria(Long id);

    void desactivarCategoria(Long id);

    List<DatosListadoCategoriaNivel> getCategoriasByNivel(int nivel);

}

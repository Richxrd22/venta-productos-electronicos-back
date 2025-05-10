package com.neon.sve.service.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.categoria.DatosActualizarCategoria;
import com.neon.sve.dto.categoria.DatosListadoCategoria;
import com.neon.sve.dto.categoria.DatosRegistroCategoria;
import com.neon.sve.dto.categoria.DatosRespuestaCategoria;

public interface CategoriaService {
    
    DatosRespuestaCategoria getCategoriaById(Long id);

    Page<DatosListadoCategoria> getAllCategoria(Pageable pageable);

    DatosRespuestaCategoria createCategoria(DatosRegistroCategoria datosRegistroCategoria);

    DatosRespuestaCategoria updateCategoria(DatosActualizarCategoria datosActualizarCategoria);

    void activarCategoria(Long id);

    void desactivarCategoria(Long id);

}

package com.neon.sve.service.subcategoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.subCategoria.DatosActualizarSubCategoria;
import com.neon.sve.dto.subCategoria.DatosListadoSubCategoria;
import com.neon.sve.dto.subCategoria.DatosRegistroSubCategoria;
import com.neon.sve.dto.subCategoria.DatosRespuestaSubCategoria;

public interface SubCategoriaService {

    DatosRespuestaSubCategoria getSubCategoriaById(Long id);

    Page<DatosListadoSubCategoria> getAllSubCategoria(Pageable pageable);

    DatosRespuestaSubCategoria createSubCategoria(DatosRegistroSubCategoria datosRegistroSubCategoria);

    DatosRespuestaSubCategoria updateSubCategoria(DatosActualizarSubCategoria datosActualizarSubCategoria);

    void activarSubCategoria(Long id);

    void desactivarSubCategoria(Long id);

}

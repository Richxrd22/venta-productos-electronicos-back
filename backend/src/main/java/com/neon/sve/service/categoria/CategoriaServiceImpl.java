package com.neon.sve.service.categoria;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.categoria.DatosActualizarCategoria;
import com.neon.sve.dto.categoria.DatosListadoCategoria;
import com.neon.sve.dto.categoria.DatosRegistroCategoria;
import com.neon.sve.dto.categoria.DatosRespuestaCategoria;
import com.neon.sve.model.Producto.Categoria;
import com.neon.sve.repository.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public DatosRespuestaCategoria getCategoriaById(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {

            Categoria categoria = categoriaOptional.get();
            return new DatosRespuestaCategoria(categoria);

        } else {
            throw new RuntimeException("Categoria no encontrada");

        }
    }

    @Override
    public Page<DatosListadoCategoria> getAllCategoria(Pageable pageable) {
        Page<Categoria> categoriaPage = categoriaRepository.findAll(pageable);
        return categoriaPage.map(DatosListadoCategoria::new);

    }

    @Override
    public DatosRespuestaCategoria createCategoria(DatosRegistroCategoria datosRegistroCategoria) {
        Categoria categoria = categoriaRepository.save(new Categoria(datosRegistroCategoria));
        return new DatosRespuestaCategoria(categoria);
    }

    @Override
    public DatosRespuestaCategoria updateCategoria(DatosActualizarCategoria datosActualizarCategoria) {
        Categoria categoria = categoriaRepository.getReferenceById(datosActualizarCategoria.id());
        categoria.actualizar(datosActualizarCategoria);
        categoria = categoriaRepository.save(categoria);
        return new DatosRespuestaCategoria(categoria);
    }

    @Override
    public void activarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Categoria no encontrrada con el ID ingresado :" + id));

        if (!categoria.getActivo()) {
            categoria.setActivo(true);
            categoriaRepository.save(categoria);
        }
    }

    @Override
    public void desactivarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Categoria no encontrada con el ID ingresado : " + id));

        if (categoria.getActivo()) {
            categoria.setActivo(false);
            categoriaRepository.save(categoria);
        }
    }

}

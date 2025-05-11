package com.neon.sve.service.categoria;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada");
        }
    }

    @Override
    public Page<DatosListadoCategoria> getAllCategoria(Pageable pageable) {
        Page<Categoria> categoriaPage = categoriaRepository.findAll(pageable);
        return categoriaPage.map(DatosListadoCategoria::new);

    }

    @Override
    public DatosRespuestaCategoria createCategoria(DatosRegistroCategoria datosRegistroCategoria) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNombre(datosRegistroCategoria.nombre());
        if (categoriaOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe una categoría con el nombre ingresado: " + datosRegistroCategoria.nombre());
        }
        Categoria categoria = categoriaRepository.save(new Categoria(datosRegistroCategoria));
        return new DatosRespuestaCategoria(categoria);
    }

    @Override
    public DatosRespuestaCategoria updateCategoria(DatosActualizarCategoria datosActualizarCategoria) {
        Optional<Categoria> existente = categoriaRepository.findByNombre(datosActualizarCategoria.nombre());

        if (existente.isPresent() && !existente.get().getId().equals(datosActualizarCategoria.id())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ya existe una categoría con el nombre: " + datosActualizarCategoria.nombre());
        }

        Categoria categoria = categoriaRepository.getReferenceById(datosActualizarCategoria.id());
        categoria.actualizar(datosActualizarCategoria);
        categoria = categoriaRepository.save(categoria);
        return new DatosRespuestaCategoria(categoria);
    }

    @Override
    public void activarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoría no encontrada con el ID ingresado: " + id));
        
                        if (Boolean.TRUE.equals(categoria.getActivo()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La categoría ya está activa");
        
            categoria.setActivo(true);
        categoriaRepository.save(categoria);
    }

    @Override
    public void desactivarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Categoría no encontrada con el ID ingresado: " + id));

        if (!categoria.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La categoría ya se encuentra desactivada");
        }

        boolean tieneSubcategoriasActivas = categoria.getSubcategorias().stream()
                .anyMatch(subcategoria -> Boolean.TRUE.equals(subcategoria.getActivo()));

        if (tieneSubcategoriasActivas) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede desactivar la categoría porque tiene subcategorías activas");
        }

        categoria.setActivo(false);
        categoriaRepository.save(categoria);
    }

}

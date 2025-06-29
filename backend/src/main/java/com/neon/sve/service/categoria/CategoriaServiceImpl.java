package com.neon.sve.service.categoria;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.categoria.DatosActualizarCategoria;
import com.neon.sve.dto.categoria.DatosListadoCategoria;
import com.neon.sve.dto.categoria.DatosListadoCategoriaNivel;
import com.neon.sve.dto.categoria.DatosListadoDetalleCategorias;
import com.neon.sve.dto.categoria.DatosRegistroCategoria;
import com.neon.sve.dto.categoria.DatosRespuestaCategoria;
import com.neon.sve.model.producto.Categoria;
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
    @Transactional
    public DatosRespuestaCategoria createCategoria(DatosRegistroCategoria datos) {
        // Valida si ya existe una categoría con el mismo nombre
        if (categoriaRepository.findByNombre(datos.nombre()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe una categoría con ese nombre.");
        }

        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre(datos.nombre());
        nuevaCategoria.setNivel(datos.nivel());

        // ASIGNAR LA CATEGORÍA PADRE
        if (datos.id_categoria_padre() != null) {
            Categoria categoriaPadre = categoriaRepository.findById(datos.id_categoria_padre())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "La categoría padre con ID " + datos.id_categoria_padre() + " no existe."));
            nuevaCategoria.setCategoriaPadre(categoriaPadre);
        }

        Categoria categoriaGuardada = categoriaRepository.save(nuevaCategoria);
        return new DatosRespuestaCategoria(categoriaGuardada);
    }

    @Override
    @Transactional
    public DatosRespuestaCategoria updateCategoria(DatosActualizarCategoria datos) {
        Categoria categoriaAActualizar = categoriaRepository.findById(datos.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada."));

        categoriaAActualizar.setNombre(datos.nombre());
        categoriaAActualizar.setNivel(datos.nivel());

        // ACTUALIZAR LA CATEGORÍA PADRE
        if (datos.id_categoria_padre() != null) {
            Categoria categoriaPadre = categoriaRepository.findById(datos.id_categoria_padre())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "La categoría padre con ID " + datos.id_categoria_padre() + " no existe."));
            categoriaAActualizar.setCategoriaPadre(categoriaPadre);
        } else {
            categoriaAActualizar.setCategoriaPadre(null); // Permite quitar la categoría padre
        }

        // El método save(guardar) actualiza si la entidad ya existe
        Categoria categoriaActualizada = categoriaRepository.save(categoriaAActualizar);
        return new DatosRespuestaCategoria(categoriaActualizada);
    }

    @Override
    public void activarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoría no encontrada con el ID ingresado: " + id));

        if (Boolean.TRUE.equals(categoria.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La categoría ya está activa");
        }
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

        categoria.setActivo(false);
        categoriaRepository.save(categoria);
    }

    @Override
    public List<DatosListadoCategoriaNivel> getCategoriasByNivel(int nivel) {
        List<Categoria> categorias = categoriaRepository.findByNivel(nivel);
        return categorias.stream()
                .map(DatosListadoCategoriaNivel::new)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DatosListadoDetalleCategorias> getAllCategoriaDetalle(Pageable pageable) {
        Page<Categoria> categoriaPage = categoriaRepository.findAll(pageable);
        return categoriaPage.map(DatosListadoDetalleCategorias::new);
    }

}

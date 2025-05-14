package com.neon.sve.service.subCategoria;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.subCategoria.DatosActualizarSubCategoria;
import com.neon.sve.dto.subCategoria.DatosListadoSubCategoria;
import com.neon.sve.dto.subCategoria.DatosRegistroSubCategoria;
import com.neon.sve.dto.subCategoria.DatosRespuestaSubCategoria;
import com.neon.sve.model.producto.Categoria;
import com.neon.sve.model.producto.SubCategoria;
import com.neon.sve.repository.CategoriaRepository;
import com.neon.sve.repository.SubCategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SubCategoriaServiceImpl implements SubCategoriaService {

    @Autowired
    private SubCategoriaRepository subCategoriaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public DatosRespuestaSubCategoria getSubCategoriaById(Long id) {
        Optional<SubCategoria> subcategoriaOptional = subCategoriaRepository.findById(id);
        if (subcategoriaOptional.isPresent()) {

            SubCategoria subCategoria = subcategoriaOptional.get();
            return new DatosRespuestaSubCategoria(subCategoria);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SubCategoria no encontrada");

        }
    }

    @Override
    public Page<DatosListadoSubCategoria> getAllSubCategoria(Pageable pageable) {
        Page<SubCategoria> SubCategoriaPage = subCategoriaRepository.findAll(pageable);
        return SubCategoriaPage.map(DatosListadoSubCategoria::new);

    }

    @Override
    public DatosRespuestaSubCategoria createSubCategoria(DatosRegistroSubCategoria datosRegistroSubCategoria) {
        Optional<SubCategoria> subCategorOptional = subCategoriaRepository
                .findByNombre(datosRegistroSubCategoria.nombre());
        if (subCategorOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe la SubCategoria con el nombre ingresado : " + datosRegistroSubCategoria.nombre());
        }

        Categoria categoria = categoriaRepository.getReferenceById(datosRegistroSubCategoria.id_categoria());
        SubCategoria subCategoria = subCategoriaRepository.save(new SubCategoria(datosRegistroSubCategoria, categoria));
        return new DatosRespuestaSubCategoria(subCategoria);
    }

    @Override
    public DatosRespuestaSubCategoria updateSubCategoria(DatosActualizarSubCategoria datosActualizarSubCategoria) {
        Optional<SubCategoria> subCategorOptional = subCategoriaRepository
                .findByNombre(datosActualizarSubCategoria.nombre());
        if (subCategorOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe la SubCategoria con el nombre ingresado : " + datosActualizarSubCategoria.nombre());
        }

        Categoria categoria = categoriaRepository.getReferenceById(datosActualizarSubCategoria.id_categoria());
        SubCategoria subCategoria = subCategoriaRepository.getReferenceById(datosActualizarSubCategoria.id());
        subCategoria.actualizar(datosActualizarSubCategoria, categoria);
        subCategoria = subCategoriaRepository.save(subCategoria);
        return new DatosRespuestaSubCategoria(subCategoria);
    }

    @Override
    public void activarSubCategoria(Long id) {
        SubCategoria subCategoria = subCategoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "SubCategoria no encontrada, verifique el ID ingresado : " + id));

        if (Boolean.TRUE.equals(subCategoria.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La SubCategoria ya esta activa");
        }

        if (Boolean.FALSE.equals(subCategoria.getId_categoria().getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede activar la SubCategoría porque la Categoría está inactiva");
        }

        subCategoria.setActivo(true);
        subCategoriaRepository.save(subCategoria);
    }

    @Override
    public void desactivarSubCategoria(Long id) {
        SubCategoria subCategoria = subCategoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "SubCategoria no encontrada, verifique el ID ingresado : " + id));

        if (!subCategoria.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La SubCategoria ya se encuentra desactivada");
        }

        boolean tieneProductosActivos = subCategoria.getProductos().stream()
                .anyMatch(producto -> Boolean.TRUE.equals(producto.getActivo()));

        if (tieneProductosActivos) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede desactivar la SubCategoría porque tiene productos activos");
        }
        
        subCategoria.setActivo(false);
        subCategoriaRepository.save(subCategoria);
    }

}

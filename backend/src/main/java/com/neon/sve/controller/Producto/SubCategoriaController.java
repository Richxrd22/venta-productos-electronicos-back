package com.neon.sve.controller.Producto;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.neon.sve.dto.subCategoria.DatosActualizarSubCategoria;
import com.neon.sve.dto.subCategoria.DatosListadoSubCategoria;
import com.neon.sve.dto.subCategoria.DatosRegistroSubCategoria;
import com.neon.sve.dto.subCategoria.DatosRespuestaSubCategoria;
import com.neon.sve.service.subCategoria.SubCategoriaService;
import com.neon.sve.service.usuario.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/subcategoria")
public class SubCategoriaController {

    @Autowired
    private SubCategoriaService subCategoriaService;

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoSubCategoria>> listadoSubCategoria(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable paginacion) {
        Page<DatosListadoSubCategoria> subCategoriaPage = subCategoriaService.getAllSubCategoria(paginacion);
        return ResponseEntity.ok(subCategoriaPage);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarSubCategoria(@PathVariable Long id) {
        try {
            DatosRespuestaSubCategoria subCategoria = subCategoriaService.getSubCategoriaById(id);
            return ResponseEntity.ok(subCategoria);
        } catch (Exception e) {
            String mensajeError = "Error al obtener la SubCategoria, verifique el ID :" + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);

        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaSubCategoria> registrarSubCateria(
            @Valid @RequestBody DatosRegistroSubCategoria datosRegistroSubCategoria,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaSubCategoria datosRespuestaSubCategoria = subCategoriaService
                .createSubCategoria(datosRegistroSubCategoria);
        URI url = uriComponentsBuilder.path("/buscar/{id}")
                .buildAndExpand(datosRespuestaSubCategoria.id())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaSubCategoria);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaSubCategoria> actualizarSubCategoria(
            @Valid @RequestBody DatosActualizarSubCategoria datosActualizarSubCategoria) {
        DatosRespuestaSubCategoria datosRespuestaSubCategoria = subCategoriaService
                .updateSubCategoria(datosActualizarSubCategoria);
        return ResponseEntity.ok(datosRespuestaSubCategoria);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<String> activarSubCategoria(@PathVariable Long id) {
        subCategoriaService.activarSubCategoria(id);
        return ResponseEntity.ok("SubCategoria activada correctamente");
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<String> desactivarSubCategoria(@PathVariable Long id) {
        subCategoriaService.desactivarSubCategoria(id);
        return ResponseEntity.ok("SubCategoria desactivada correctamente");
    }

}

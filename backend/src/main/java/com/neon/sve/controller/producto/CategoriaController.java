package com.neon.sve.controller.producto;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import com.neon.sve.dto.MensajeRespuesta;
import com.neon.sve.dto.categoria.DatosActualizarCategoria;
import com.neon.sve.dto.categoria.DatosListadoCategoria;
import com.neon.sve.dto.categoria.DatosListadoCategoriaNivel;
import com.neon.sve.dto.categoria.DatosListadoDetalleCategorias;
import com.neon.sve.dto.categoria.DatosRegistroCategoria;
import com.neon.sve.dto.categoria.DatosRespuestaCategoria;
import com.neon.sve.service.categoria.CategoriaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoCategoria>> listarCategoria() {
        Pageable paginacion = Pageable.unpaged();
        List<DatosListadoCategoria> categorias = categoriaService.getAllCategoria(paginacion).getContent();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/listar-detalle")
    public ResponseEntity<List<DatosListadoDetalleCategorias>> listarCategoriaDetalle() {
        Pageable paginacion = Pageable.unpaged();
        List<DatosListadoDetalleCategorias> categorias = categoriaService.getAllCategoriaDetalle(paginacion).getContent();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarCategoria(@PathVariable Long id) {
        try {
            DatosRespuestaCategoria categoria = categoriaService.getCategoriaById(id);
            return ResponseEntity.ok(categoria);

        } catch (Exception e) {
            String mensajeError = "Error al obtener el ID de la categoria : " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);

        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaCategoria> registrarCategoria(
            @Valid @RequestBody DatosRegistroCategoria datosRegistroCategoria,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaCategoria datosRespuestaCategoria = categoriaService.createCategoria(datosRegistroCategoria);
        URI url = uriComponentsBuilder.path("/buscar/{id}")
                .buildAndExpand(datosRespuestaCategoria.id())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaCategoria);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaCategoria> actualizarCategoria(
            @Valid @RequestBody DatosActualizarCategoria actualizarCategoria) {
        DatosRespuestaCategoria datosRespuestaCategoria = categoriaService.updateCategoria(actualizarCategoria);
        return ResponseEntity.ok(datosRespuestaCategoria);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<MensajeRespuesta> activarCategoria(@PathVariable Long id) {
        categoriaService.activarCategoria(id);
        return ResponseEntity.ok(new MensajeRespuesta("Categoría activada correctamente"));
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<MensajeRespuesta> desactivarCategoria(@PathVariable Long id) {
        categoriaService.desactivarCategoria(id);
        return ResponseEntity.ok(new MensajeRespuesta("Categoría desactivada correctamente"));
    }

    @GetMapping("/listar/nivel/{nivel}")
    public ResponseEntity<List<DatosListadoCategoriaNivel>> listarCategoriasPorNivel(@PathVariable int nivel) {
        List<DatosListadoCategoriaNivel> categorias = categoriaService.getCategoriasByNivel(nivel);
        return ResponseEntity.ok(categorias);
    }

}

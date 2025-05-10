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

import com.neon.sve.dto.marca.DatosActualizarMarca;
import com.neon.sve.dto.marca.DatosListadoMarca;
import com.neon.sve.dto.marca.DatosRegistroMarca;
import com.neon.sve.dto.marca.DatosRespuestaMarca;
import com.neon.sve.service.marca.MarcaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoMarca>> listarMarca(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable paginacion) {

        Page<DatosListadoMarca> marcaPage = marcaService.getAllMarca(paginacion);
        return ResponseEntity.ok(marcaPage);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarMarca(@PathVariable Long id) {

        try {
            DatosRespuestaMarca marca = marcaService.getMarcaById(id);
            return ResponseEntity.ok(marca);

        } catch (Exception e) {

            String mensajeError = "Error al obtener la marca con el ID" + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);

        }

    }

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<DatosRespuestaMarca> registrarMarca(
            @Valid @RequestBody DatosRegistroMarca datosRegistroMarca,
            UriComponentsBuilder uriComponentsBuilder) {

        DatosRespuestaMarca datosRespuestaMarca = marcaService.createMarca(datosRegistroMarca);
        URI url = uriComponentsBuilder.path("/buscar/{id}")
                .buildAndExpand(datosRespuestaMarca.id())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaMarca);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaMarca> actualizarMarca(
            @Valid @RequestBody DatosActualizarMarca actualizarMarca) {
        DatosRespuestaMarca datosRespuestaMarca = marcaService.updateMarca(actualizarMarca);
        return ResponseEntity.ok(datosRespuestaMarca);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<String> activarMarca(@PathVariable Long id) {
        marcaService.activarMarca(id);
        return ResponseEntity.ok("Marca activada correctamente");
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<String> desactivarMarca(@PathVariable Long id) {
        marcaService.desactivarMarca(id);
        return ResponseEntity.ok("Marca desactivada correctamente");
    }

}

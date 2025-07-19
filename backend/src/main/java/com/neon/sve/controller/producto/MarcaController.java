package com.neon.sve.controller.producto;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<List<DatosListadoMarca>> listarMarca() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id"); 
        Pageable paginacion = PageRequest.of(0, Integer.MAX_VALUE, sort); 
        List<DatosListadoMarca> marcas = marcaService.getAllMarca(paginacion).getContent();
        return ResponseEntity.ok(marcas);
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
    public ResponseEntity<MensajeRespuesta> activarMarca(@PathVariable Long id) {
        marcaService.activarMarca(id);
        return ResponseEntity.ok(new MensajeRespuesta("Marca activada correctamente"));
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<MensajeRespuesta> desactivarMarca(@PathVariable Long id) {
        marcaService.desactivarMarca(id);
        return ResponseEntity.ok(new MensajeRespuesta("Marca desactivada correctamente"));
    }

}

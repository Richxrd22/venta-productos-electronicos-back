package com.neon.sve.controller.venta;

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
import com.neon.sve.dto.metodoPago.DatosActualizarMetodoPago;
import com.neon.sve.dto.metodoPago.DatosListadoMetodoPago;
import com.neon.sve.dto.metodoPago.DatosRegistroMetodoPago;
import com.neon.sve.dto.metodoPago.DatosRespuestaMetodoPago;
import com.neon.sve.dto.reclamoGarantia.DatosActualizarReclamoGarantia;
import com.neon.sve.dto.reclamoGarantia.DatosListadoReclamoGarantia;
import com.neon.sve.dto.reclamoGarantia.DatosRegistroReclamoGarantia;
import com.neon.sve.dto.reclamoGarantia.DatosRespuestaReclamoGarantia;
import com.neon.sve.service.reclamoGarantia.ReclamoGarantiaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/reclamo-garantia")
public class ReclamoGarantiaController {

    @Autowired
    private ReclamoGarantiaService reclamoGarantiaService;

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoReclamoGarantia>> listarReclamosGarantia() {
        Pageable pageable = Pageable.unpaged();
        List<DatosListadoReclamoGarantia> reclamosList = reclamoGarantiaService.getAllReclamoGarantia(pageable)
                .getContent();
        return ResponseEntity.ok(reclamosList);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarReclamoPorId(@PathVariable Long id) {
        try {
            DatosRespuestaReclamoGarantia reclamo = reclamoGarantiaService.getReclamoGarantiaById(id);
            return ResponseEntity.ok(reclamo);
        } catch (Exception e) {
            String mensajeError = "Error al obtener el metodo de pago con el ID" + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }

    }

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaReclamoGarantia> registraReclamoGarantia(
            @Valid @RequestBody DatosRegistroReclamoGarantia datosRegistrarReclamoGarantia,
            UriComponentsBuilder uriBuilder) {
        DatosRespuestaReclamoGarantia reclamo = reclamoGarantiaService
                .createReclamoGarantia(datosRegistrarReclamoGarantia);
        URI uri = uriBuilder.path("/buscar/{id}")
                .buildAndExpand(reclamo.id_reclamo_garantia())
                .toUri();
        return ResponseEntity.created(uri).body(reclamo);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaReclamoGarantia> actualizarReclamoGarantia(
            @Valid @RequestBody DatosActualizarReclamoGarantia datosActualizarReclamoGarantia) {
        DatosRespuestaReclamoGarantia reclamoActualizado = reclamoGarantiaService
                .updateReclamoGarantia(datosActualizarReclamoGarantia);
        return ResponseEntity.ok(reclamoActualizado);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<MensajeRespuesta> activarReclamoGarantia(@PathVariable Long id) {
        reclamoGarantiaService.activarReclamoGarantia(id);
        MensajeRespuesta mensaje = new MensajeRespuesta("Reclamo de garantia activado correctamente");
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<MensajeRespuesta> desactivarReclamoGarantia(@PathVariable Long id) {
        reclamoGarantiaService.desactivarReclamoGarantia(id);
        MensajeRespuesta mensaje = new MensajeRespuesta("Reclamo de garantia desactivado correctamente");
        return ResponseEntity.ok(mensaje);
    }

}

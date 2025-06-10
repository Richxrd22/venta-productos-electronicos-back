package com.neon.sve.controller.venta;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.neon.sve.dto.MensajeRespuesta;
import com.neon.sve.dto.metodoPago.DatosActualizarMetodoPago;
import com.neon.sve.dto.metodoPago.DatosListadoMetodoPago;
import com.neon.sve.dto.metodoPago.DatosRegistroMetodoPago;
import com.neon.sve.dto.metodoPago.DatosRespuestaMetodoPago;
import com.neon.sve.service.metodoPago.MetodoPagoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/metodo-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoMetodoPago>> listarMetodoPago() {
        Pageable pageable = Pageable.unpaged();
        List<DatosListadoMetodoPago> metodoPagoList = metodoPagoService.getAllMetodoPago(pageable).getContent();
        return ResponseEntity.ok(metodoPagoList);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarMetodoPago(@PathVariable Long id) {
        try {
            DatosRespuestaMetodoPago metodoPago = metodoPagoService.getMetodoPagoById(id);
            return ResponseEntity.ok(metodoPago);
        } catch (Exception e) {

            String mensajeError = "Error al obtener el metodo de pago con el ID" + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);

        }
    }

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<DatosRespuestaMetodoPago> registrarMetodoPago(
            @Valid @RequestBody DatosRegistroMetodoPago datosRegistroMetodoPago,
            UriComponentsBuilder uriBuilder) {

        DatosRespuestaMetodoPago respuesta = metodoPagoService.createMetodoPago(datosRegistroMetodoPago);
        URI uri = uriBuilder.path("/buscar/{id}")
                .buildAndExpand(respuesta.id())
                .toUri();

        return ResponseEntity.created(uri).body(respuesta);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaMetodoPago> actualizarMetodoPago(
            @Valid @RequestBody DatosActualizarMetodoPago datosActualizarMetodoPago) {

        DatosRespuestaMetodoPago respuesta = metodoPagoService.updateMetodoPago(datosActualizarMetodoPago);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<MensajeRespuesta> activarMetodoPago(@PathVariable Long id) {
        metodoPagoService.activarMetodoPago(id);
        return ResponseEntity.ok(new MensajeRespuesta("Método de pago activado correctamente"));
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<MensajeRespuesta> desactivarMetodoPago(@PathVariable Long id) {
        metodoPagoService.desactivarMetodoPago(id);
        return ResponseEntity.ok(new MensajeRespuesta("Método de pago desactivado correctamente"));
    }
}

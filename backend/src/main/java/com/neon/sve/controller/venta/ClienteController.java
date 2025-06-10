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
import com.neon.sve.dto.cliente.DatosActualizarCliente;
import com.neon.sve.dto.cliente.DatosListadoCliente;
import com.neon.sve.dto.cliente.DatosRegistroCliente;
import com.neon.sve.dto.cliente.DatosRespuestaCliente;
import com.neon.sve.service.cliente.ClienteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoCliente>> listarClientes() {
        Pageable pageable = Pageable.unpaged();
        List<DatosListadoCliente> lista = clienteService.getAllCliente(pageable).getContent();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable Long id) {
        try {
            DatosRespuestaCliente cliente = clienteService.getClienteById(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente no encontrado: " + e.getMessage());
        }
    }

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<DatosRespuestaCliente> registrarCliente(
            @Valid @RequestBody DatosRegistroCliente datosRegistroCliente,
            UriComponentsBuilder uriBuilder) {

        DatosRespuestaCliente respuesta = clienteService.createCliente(datosRegistroCliente);
        URI uri = uriBuilder.path("/buscar/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaCliente> actualizarCliente(
            @Valid @RequestBody DatosActualizarCliente datosActualizarCliente) {

        DatosRespuestaCliente respuesta = clienteService.updateCliente(datosActualizarCliente);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<MensajeRespuesta> activarCliente(@PathVariable Long id) {
        clienteService.activarCliente(id);
        return ResponseEntity.ok(new MensajeRespuesta("Cliente activado correctamente"));
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<MensajeRespuesta> desactivarCliente(@PathVariable Long id) {
        clienteService.desactivarCliente(id);
        return ResponseEntity.ok(new MensajeRespuesta("Cliente desactivado correctamente"));
    }
}

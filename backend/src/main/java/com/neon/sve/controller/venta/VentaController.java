package com.neon.sve.controller.venta;

import com.neon.sve.dto.MensajeRespuesta;
import com.neon.sve.dto.registroVenta.DatosActualizarVentaCompleta;
import com.neon.sve.dto.registroVenta.DatosListadoRegistroVenta;
import com.neon.sve.dto.registroVenta.DatosRegistroVentaCompleta;
import com.neon.sve.dto.registroVenta.DatosRespuestaRegistroVenta;
import com.neon.sve.service.registroVenta.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoRegistroVenta>> listarVentas() {
        Pageable paginacion = Pageable.unpaged();
        List<DatosListadoRegistroVenta> ventas = ventaService.getAllVentas(paginacion).getContent();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarVenta(@PathVariable Long id) {
        try {
            DatosRespuestaRegistroVenta venta = ventaService.getVentaById(id);
            return ResponseEntity.ok(venta);
        } catch (Exception e) {
            String mensajeError = "Error al buscar la venta requerida, verifique ID ingresado: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaRegistroVenta> registrarVenta(
            @Valid @RequestBody DatosRegistroVentaCompleta datosRegistroVenta,
            UriComponentsBuilder uriComponentsBuilder) {

        // Llamamos al nuevo método del servicio
        DatosRespuestaRegistroVenta datosRespuestaVenta = ventaService.createVentaCompleta(datosRegistroVenta);

        URI url = uriComponentsBuilder.path("/buscar/{id}")
                .buildAndExpand(datosRespuestaVenta.id())
                .toUri();

        return ResponseEntity.created(url).body(datosRespuestaVenta);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaRegistroVenta> actualizarVenta(
            @Valid @RequestBody DatosActualizarVentaCompleta datosActualizar) {

        DatosRespuestaRegistroVenta datosRespuestaVenta = ventaService.updateVentaCompleta(datosActualizar);
        return ResponseEntity.ok(datosRespuestaVenta);
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<MensajeRespuesta> cancelarVenta(@PathVariable Long id) {
        ventaService.cancelarVenta(id);
        return ResponseEntity.ok(new MensajeRespuesta("Venta cancelada exitosamente"));
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<MensajeRespuesta> activarVenta(@PathVariable Long id) {
        ventaService.activarVenta(id);
        return ResponseEntity.ok(new MensajeRespuesta("Venta activada exitosamente"));
    }
}

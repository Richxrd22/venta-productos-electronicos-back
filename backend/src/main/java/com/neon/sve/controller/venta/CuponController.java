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
import com.neon.sve.dto.cupon.DatosActualizarCupon;
import com.neon.sve.dto.cupon.DatosListadoCupon;
import com.neon.sve.dto.cupon.DatosRegistroCupon;
import com.neon.sve.dto.cupon.DatosRespuestaCupon;
import com.neon.sve.model.ventas.Cupon;
import com.neon.sve.service.cupon.CuponService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cupon")
public class CuponController {

    @Autowired
    private CuponService cuponService;

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoCupon>> listarCupones() {
        Pageable pageable = Pageable.unpaged();
        List<DatosListadoCupon> cupones = cuponService.getAllCupones(pageable).getContent();
        return ResponseEntity.ok(cupones);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarCupon(@PathVariable Long id) {
        try {
            DatosRespuestaCupon cupon = cuponService.obtenerCuponPorId(id);
            return ResponseEntity.ok(cupon);
        } catch (Exception e) {
            String mensajeError = "Error al obtener la marca con el ID" + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);

        }
    }

    /*
    @GetMapping("/buscar-tipo/{tipo}")
    public ResponseEntity<?> buscarPorTipoDescuento(@PathVariable String tipo) {
        try {
            List<Cupon> cupones = cuponService.buscarPorTipoDescuento(tipo);
            return ResponseEntity.ok(cupones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al buscar cupones por tipo de descuento: " + e.getMessage());
        }
    }
        */

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<DatosRespuestaCupon> registrarCupon(
            @Valid @RequestBody DatosRegistroCupon datosRegistroCupon,
            UriComponentsBuilder uriBuilder) {
        DatosRespuestaCupon respuesta = cuponService.crearCupon(datosRegistroCupon);
        URI uri = uriBuilder.path("/buscar/{id}")
                .buildAndExpand(respuesta.id())
                .toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaCupon> actualizarCupon(
            @Valid @RequestBody DatosActualizarCupon datosActualizarCupon) {
        DatosRespuestaCupon respuesta = cuponService.actualizarCupon(datosActualizarCupon);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<MensajeRespuesta> activarCupon(@PathVariable Long id) {
        cuponService.activarDescuento(id);
        return ResponseEntity.ok(new MensajeRespuesta("Cupón activado correctamente"));
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<MensajeRespuesta> desactivarCupon(@PathVariable Long id) {
        cuponService.desactivarDescuento(id);
        return ResponseEntity.ok(new MensajeRespuesta("Cupón desactivado correctamente"));
    }

    @PutMapping("/incrementar-uso/{codigo}")
    public ResponseEntity<MensajeRespuesta> incrementarUsoCupon(@PathVariable String codigo) {
        cuponService.incrementarUsoCupon(codigo);
        return ResponseEntity.ok(new MensajeRespuesta("Uso del cupón incrementado correctamente"));
    }
}

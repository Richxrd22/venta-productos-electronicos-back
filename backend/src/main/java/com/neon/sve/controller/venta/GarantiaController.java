package com.neon.sve.controller.venta;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.neon.sve.dto.MensajeRespuesta;
import com.neon.sve.dto.garantia.DatosActualizarGarantia;
import com.neon.sve.dto.garantia.DatosListadoGarantia;
import com.neon.sve.dto.garantia.DatosRegistroGarantia;
import com.neon.sve.dto.garantia.DatosRespuestaGarantia;
import com.neon.sve.service.garantia.GarantiaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/garantia")
public class GarantiaController {

    @Autowired
    private GarantiaService garantiaService;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaGarantia> registrarGarantia(
            @Valid @RequestBody DatosRegistroGarantia datosRegistroGarantia,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaGarantia datosRespuestaGarantia = garantiaService.createGarantia(datosRegistroGarantia);
        URI url = uriComponentsBuilder.path("/garantia/buscar/{id}")
                .buildAndExpand(datosRespuestaGarantia.id_garantia())
                .toUri();

        return ResponseEntity.created(url).body(datosRespuestaGarantia);

    }

    @PutMapping("/actualizar")
    public ResponseEntity<DatosRespuestaGarantia> actualizarGarantia(
            @Valid @RequestBody DatosActualizarGarantia datosActualizarGarantia) {
        DatosRespuestaGarantia datosRespuestaGarantia = garantiaService.updateGarantia(datosActualizarGarantia);
        return ResponseEntity.ok(datosRespuestaGarantia);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            DatosRespuestaGarantia garantia = garantiaService.buscarPorId(id);
            return ResponseEntity.ok(garantia);
        } catch (Exception e) {
            String mensajeError = "Error al buscar el descuento, verifique ID ingresado : " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);

        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoGarantia>> listarPorDetalleVenta() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id_garantia");
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);

        List<DatosListadoGarantia> listaGarantias = garantiaService.listarPorDetalleVenta(pageable).getContent();
        return ResponseEntity.ok(listaGarantias);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<MensajeRespuesta> activarGarantia(@PathVariable Long id) {
        garantiaService.activarGarantia(id);
        return ResponseEntity.ok(new MensajeRespuesta("Garantía activada correctamente"));
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<MensajeRespuesta> desactivarGarantia(@PathVariable Long id) {
        garantiaService.desactivarGarantia(id);
        return ResponseEntity.ok(new MensajeRespuesta("Garantía desactivada correctamente"));
    }
}

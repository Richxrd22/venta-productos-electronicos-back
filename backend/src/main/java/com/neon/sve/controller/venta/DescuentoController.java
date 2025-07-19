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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neon.sve.dto.MensajeRespuesta;
import com.neon.sve.dto.descuento.DatosActualizarDescuento;
import com.neon.sve.dto.descuento.DatosListadoDescuento;
import com.neon.sve.dto.descuento.DatosRegistroDescuento;
import com.neon.sve.dto.descuento.DatosRespuestaDescuento;
import com.neon.sve.service.descuento.DescuentoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequestMapping("/descuento")
public class DescuentoController {

    @Autowired
    private DescuentoService descuentoService;

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoDescuento>> listarDescuento() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id"); 
        Pageable paginacion = PageRequest.of(0, Integer.MAX_VALUE, sort);

        List<DatosListadoDescuento> descuentos = descuentoService.getAllDescuento(paginacion).getContent();
        return ResponseEntity.ok(descuentos);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarDescuento(@PathVariable Long id) {
        try {
            DatosRespuestaDescuento descuento = descuentoService.getDescuentoById(id);
            return ResponseEntity.ok(descuento);
        } catch (Exception e) {
            String mensajeError = "Error al buscar el descuento, verifique ID ingresado : " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);

        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaDescuento> registrarDescuento(
            @Valid @RequestBody DatosRegistroDescuento datosRegistroDescuento,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaDescuento datosRespuestaDescuento = descuentoService.createDescuento(datosRegistroDescuento);
        URI url = uriComponentsBuilder.path("/buscar/{id}")
                .buildAndExpand(datosRespuestaDescuento.id())
                .toUri();

        return ResponseEntity.created(url).body(datosRespuestaDescuento);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaDescuento> actualizarDescuento(
            @Valid @RequestBody DatosActualizarDescuento datosActualizarDescuento) {

        DatosRespuestaDescuento datosRespuestaDescuento = descuentoService.updateDescuento(datosActualizarDescuento);
        return ResponseEntity.ok(datosRespuestaDescuento);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<MensajeRespuesta> activarDescuento(@PathVariable Long id) {
        descuentoService.activarDescuento(id);
        return ResponseEntity.ok(new MensajeRespuesta("Descuento activado correctamente"));
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<MensajeRespuesta> desactivarDesceunto(@PathVariable Long id) {
        descuentoService.desactivarDescuento(id);
        return ResponseEntity.ok(new MensajeRespuesta("Descuento desactivado correctamente"));
    }

}

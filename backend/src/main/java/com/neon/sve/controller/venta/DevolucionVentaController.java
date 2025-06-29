package com.neon.sve.controller.venta;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.neon.sve.dto.devolucionVenta.DatosActualizarDevolucionVenta;
import com.neon.sve.dto.devolucionVenta.DatosListadoDevolucionVenta;
import com.neon.sve.dto.devolucionVenta.DatosRegistroDevolucionVenta;
import com.neon.sve.dto.devolucionVenta.DatosRespuestaDevolucionVenta;
import com.neon.sve.service.devolucionVenta.DevolucionVentaService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/devolucion-venta")
public class DevolucionVentaController {

    @Autowired
    private DevolucionVentaService devolucionVentaService;

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoDevolucionVenta>> listarDevoluciones(Pageable pageable) {
        Page<DatosListadoDevolucionVenta> devoluciones = devolucionVentaService.getAllDevolucionVenta(pageable);
        return ResponseEntity.ok(devoluciones.getContent());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarDevolucion(@PathVariable Long id) {
        try {
            DatosRespuestaDevolucionVenta devolucion = devolucionVentaService.getDevolucionVentaById(id);
            return ResponseEntity.ok(devolucion);
        } catch (Exception e) {
            String mensajeError = "Error al buscar la devolución requerida, verifique ID ingresado: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaDevolucionVenta> registrarDevolucion(
            @Valid @RequestBody DatosRegistroDevolucionVenta datosRegistroDevolucion,
            UriComponentsBuilder uriComponentsBuilder) {

        // Llamamos al nuevo método del servicio
        DatosRespuestaDevolucionVenta datosRespuestaDevolucion = devolucionVentaService
                .createDevolucionVenta(datosRegistroDevolucion);

        URI url = uriComponentsBuilder.path("/buscar/{id}")
                .buildAndExpand(datosRespuestaDevolucion.id())
                .toUri();

        return ResponseEntity.created(url).body(datosRespuestaDevolucion);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<DatosRespuestaDevolucionVenta> actualizarDevolucion(
            @Valid @RequestBody DatosActualizarDevolucionVenta datosActualizarDevolucion) {

        DatosRespuestaDevolucionVenta datosRespuestaDevolucion = devolucionVentaService
                .updateDevolucionVenta(datosActualizarDevolucion);
        return ResponseEntity.ok(datosRespuestaDevolucion);
    }

}

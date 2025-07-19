package com.neon.sve.controller.stock;

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

import com.neon.sve.dto.MensajeRespuesta;
import com.neon.sve.dto.devolucionProducto.DatosActualizarDevolucionProducto;
import com.neon.sve.dto.devolucionProducto.DatosListadoDevolucionProducto;
import com.neon.sve.dto.devolucionProducto.DatosRegistroDevolucionProducto;
import com.neon.sve.dto.devolucionProducto.DatosRespuestaDevolucionProducto;
import com.neon.sve.dto.login.DatosRespuestaMensaje;
import com.neon.sve.service.devolucionProducto.DevolucionProductoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/devolucion-producto")
public class DevolucionProductoController {

    @Autowired
    private DevolucionProductoService devolucionProductoService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> getDevolucionById(@PathVariable Long id) {

        try {
            DatosRespuestaDevolucionProducto devolucionProducto = devolucionProductoService
                    .getDevolucionProductoById(id);
            return ResponseEntity.ok(devolucionProducto);
        } catch (Exception e) {
            String mensajeError = "Error al obtener la Devolucion con el ID " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoDevolucionProducto>> getAllDevoluciones(Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id_devolucion_producto"); 
        Pageable paginacion = PageRequest.of(0, Integer.MAX_VALUE, sort);
        List<DatosListadoDevolucionProducto> listadoDevolucionProductos = devolucionProductoService
                .getAllDevoluciones(paginacion).getContent();
        return ResponseEntity.ok(listadoDevolucionProductos);
    }

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaMensaje> registrarDevolucionProducto(
            @Valid @RequestBody DatosRegistroDevolucionProducto datos) {
        DatosRespuestaMensaje respuesta = devolucionProductoService.registrarDevolucionProducto(datos);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<DatosRespuestaMensaje> actualizarDevolucionProducto(
            @Valid @RequestBody DatosActualizarDevolucionProducto datos) {
        DatosRespuestaMensaje respuesta = devolucionProductoService.actualizarDevolucionProducto(datos);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<MensajeRespuesta> activarDevolucionProducto(@PathVariable Long id) {
        devolucionProductoService.activarDevolucionProducto(id);
        return ResponseEntity.ok(new MensajeRespuesta("Devolución activada correctamente"));
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<MensajeRespuesta> desactivarDevolucionProducto(@PathVariable Long id) {
        devolucionProductoService.desactivarDevolucionProducto(id);
        return ResponseEntity.ok(new MensajeRespuesta("Devolución desactivada correctamente"));
    }

}

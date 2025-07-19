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
import com.neon.sve.dto.producto.DatosActualizarProducto;
import com.neon.sve.dto.producto.DatosListadoProducto;
import com.neon.sve.dto.producto.DatosRegistroProducto;
import com.neon.sve.dto.producto.DatosRespuestaProducto;
import com.neon.sve.service.producto.ProductoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoProducto>> listarProducto() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable paginacion = PageRequest.of(0, Integer.MAX_VALUE, sort);
        List<DatosListadoProducto> productos = productoService.getAllProducto(paginacion).getContent();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarProducto(@PathVariable Long id) {
        try {
            DatosRespuestaProducto producto = productoService.getProductoById(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            String mensajeError = "Error al buscar el producto requerido, verifique ID ingresado : " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);

        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaProducto> registroProducto(
            @Valid @RequestBody DatosRegistroProducto datosRegistroProducto,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaProducto datosRespuestaProducto = productoService.createProducto(datosRegistroProducto);
        URI url = uriComponentsBuilder.path("/buscar/{id}")
                .buildAndExpand(datosRespuestaProducto.id())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaProducto);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaProducto> actualizarProducto(
            @Valid @RequestBody DatosActualizarProducto datosActualizarProducto) {
        DatosRespuestaProducto datosRespuestaProducto = productoService.updateProducto(datosActualizarProducto);
        return ResponseEntity.ok(datosRespuestaProducto);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<MensajeRespuesta> activarProducto(@PathVariable Long id) {
        productoService.activarProducto(id);
        return ResponseEntity.ok(new MensajeRespuesta("Producto activado exitosamente"));
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<MensajeRespuesta> desactivarProducto(@PathVariable Long id) {
        productoService.desactivarProducto(id);
        return ResponseEntity.ok(new MensajeRespuesta("Producto desactivado exitosamente"));
    }

}

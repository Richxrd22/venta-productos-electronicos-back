package com.neon.sve.controller.producto;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neon.sve.dto.proveedor.DatosActualizarProveedor;
import com.neon.sve.dto.proveedor.DatosListadoProveedor;
import com.neon.sve.dto.proveedor.DatosRegistroProveedor;
import com.neon.sve.dto.proveedor.DatosRespuestaProveedor;
import com.neon.sve.service.proveedor.ProveedorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoProveedor>> ListarProveedores() {
        Pageable paginacion = Pageable.unpaged();
        List<DatosListadoProveedor> proveedores = proveedorService.getAllProveedores(paginacion).getContent();
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarProveedor(@PathVariable Long id) {
        try {
            DatosRespuestaProveedor proveedores = proveedorService.getProveedroById(id);
            return ResponseEntity.ok(proveedores);
        } catch (Exception e) {
            String mensajeError = "Error al obtener al proveedor con ID" + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PostMapping("/registrar")
    // @Transactional
    public ResponseEntity<DatosRespuestaProveedor> registroProveedor(
            @Valid @RequestBody DatosRegistroProveedor datosRegistroProveedores,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaProveedor datosRespuestaProveedores = proveedorService
                .createProveedores(datosRegistroProveedores);
        URI url = uriComponentsBuilder.path("/buscar/{id}")
                .buildAndExpand(datosRespuestaProveedores.id())
                .toUri();

        return ResponseEntity.created(url).body(datosRespuestaProveedores);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaProveedor> actualizarProveedor(
            @Valid @RequestBody DatosActualizarProveedor datosActualizarProveedores) {
        DatosRespuestaProveedor datosRespuestaProveedores = proveedorService
                .updateProveedores(datosActualizarProveedores);
        return ResponseEntity.ok(datosRespuestaProveedores);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<String> activarProveedor(@PathVariable Long id) {
        proveedorService.activarProveedor(id);
        return ResponseEntity.ok("Proveedor activado correctamente");
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<String> desactivarProveedor(@PathVariable Long id) {
        proveedorService.desactivarProveedor(id);
        return ResponseEntity.ok("Proveedor desactivado correctamente");
    }

}

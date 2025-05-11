package com.neon.sve.controller.producto;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private ProveedorService proveedoreService;

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoProveedor>> ListarProveedores(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable pagination) {
        Page<DatosListadoProveedor> proveedoresPage = proveedoreService.getAllProveedores(pagination);
        return ResponseEntity.ok(proveedoresPage);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarProveedor(@PathVariable Long id) {
        try {
            DatosRespuestaProveedor proveedores = proveedoreService.getProveedroById(id);
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
        DatosRespuestaProveedor datosRespuestaProveedores = proveedoreService
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
        DatosRespuestaProveedor datosRespuestaProveedores = proveedoreService
                .updateProveedores(datosActualizarProveedores);
        return ResponseEntity.ok(datosRespuestaProveedores);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<String> activarProveedor(@PathVariable Long id) {
        proveedoreService.activarProveedor(id);
        return ResponseEntity.ok("Proveedor activado correctamente");
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<String> desactivarProveedor(@PathVariable Long id) {
        proveedoreService.desactivarProveedor(id);
        return ResponseEntity.ok("Proveedor desactivado correctamente");
    }

}

package com.neon.sve.controller.Producto;

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

import com.neon.sve.dto.Proveedores.DatosActualizarProveedores;
import com.neon.sve.dto.Proveedores.DatosListadoProveedores;
import com.neon.sve.dto.Proveedores.DatosRegistroProveedores;
import com.neon.sve.dto.Proveedores.DatosRespuestaProveedores;
import com.neon.sve.service.Proveedores.ProveedoreService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/proveedores")
public class ProveedoresController {

    @Autowired
    private ProveedoreService proveedoreService;

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoProveedores>> ListarProveedores(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable pagination) {
        Page<DatosListadoProveedores> proveedoresPage = proveedoreService.getAllProveedores(pagination);
        return ResponseEntity.ok(proveedoresPage);
    }

    @GetMapping("/buscar/{id_proveedor}")
    public ResponseEntity<?> buscarProveedor(@PathVariable Long id_proveedor) {
        try {
            DatosRespuestaProveedores proveedores = proveedoreService.getProveedroById(id_proveedor);
            return ResponseEntity.ok(proveedores);
        } catch (Exception e) {
            String mensajeError = "Error al obtener al proveedor con ID" + id_proveedor;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PostMapping("/registrar")
    // @Transactional
    public ResponseEntity<DatosRespuestaProveedores> registroProveedor(
            @Valid @RequestBody DatosRegistroProveedores datosRegistroProveedores,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaProveedores datosRespuestaProveedores = proveedoreService
                .createProveedores(datosRegistroProveedores);
        URI url = uriComponentsBuilder.path("/buscar/{id_proveedor}")
                .buildAndExpand(datosRespuestaProveedores.id_proveedor())
                .toUri();

        return ResponseEntity.created(url).body(datosRespuestaProveedores);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaProveedores> actualizarProveedor(
            @Valid @RequestBody DatosActualizarProveedores datosActualizarProveedores) {
        DatosRespuestaProveedores datosRespuestaProveedores = proveedoreService
                .updateProveedores(datosActualizarProveedores);
        return ResponseEntity.ok(datosRespuestaProveedores);
    }

    @PutMapping("/activar/{id_proveedor}")
    public ResponseEntity<String> activarProveedor(@PathVariable Long id) {
        proveedoreService.activarProveedor(id);
        return ResponseEntity.ok("Proveedor activado correctamente");
    }

    @PutMapping("/desactivar/{id_proveedor}")
    public ResponseEntity<String> desactivarProveedor(@PathVariable Long id) {
        proveedoreService.desactivarProveedor(id);
        return ResponseEntity.ok("Proveedor desactivado correctamente");
    }

}

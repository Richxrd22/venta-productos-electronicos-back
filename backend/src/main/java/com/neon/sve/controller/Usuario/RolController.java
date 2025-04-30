package com.neon.sve.controller.Usuario;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.neon.sve.dto.rol.DatosActualizarRol;
import com.neon.sve.dto.rol.DatosListadoRol;
import com.neon.sve.dto.rol.DatosRegistroRol;
import com.neon.sve.dto.rol.DatosRespuestaRol;
import com.neon.sve.service.rol.RolService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rol")
public class RolController {
    
    @Autowired
    private RolService rolService;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaRol> registrarRol(
            @Valid @RequestBody DatosRegistroRol datosRegistroRol,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaRol datosRespuestaRol = rolService.createRol(datosRegistroRol);
        URI url = uriComponentsBuilder.path("/rol/buscar/{id_rol}")
                .buildAndExpand(datosRespuestaRol.id_rol())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaRol);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoRol>> listarRoles(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable paginacion ) {
        Page<DatosListadoRol> proveedorPage = rolService.getAllRoles(paginacion);
        return ResponseEntity.ok(proveedorPage);
    }

    
    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaRol> actualizarRol(
            @Valid @RequestBody DatosActualizarRol datosActualizarRol) {
        DatosRespuestaRol datosRespuestaRol = rolService.updateRol(datosActualizarRol);
        return ResponseEntity.ok(datosRespuestaRol);
    }

    @GetMapping("/buscar/{id_rol}")
    public ResponseEntity<?> buscarRol(@PathVariable Long id_rol) {
        try {
            DatosRespuestaRol rol = rolService.getRolById(id_rol);
            return ResponseEntity.ok(rol);
        } catch (Exception e) {
            String mensajeError = "Error al obtener el Rol con ID " + id_rol;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @DeleteMapping("/eliminar/{id_rol}")
    public ResponseEntity<?> eliminarRol(@PathVariable Long id_rol) {
        try {
            rolService.deleteRol(id_rol);
            return ResponseEntity.ok("Eliminación exitosa");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

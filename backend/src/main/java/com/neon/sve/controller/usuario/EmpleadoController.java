package com.neon.sve.controller.usuario;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import com.neon.sve.dto.empleado.DatosActualizarEmpleado;
import com.neon.sve.dto.empleado.DatosListadoEmpleado;
import com.neon.sve.dto.empleado.DatosRegistroEmpleado;
import com.neon.sve.dto.empleado.DatosRespuestaEmpleado;
import com.neon.sve.service.empleado.EmpleadoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaEmpleado> registrarEmpleado(
            @Valid @RequestBody DatosRegistroEmpleado datosRegistroEmpleado,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaEmpleado datosRespuestaEmpleado = empleadoService.createEmpleado(datosRegistroEmpleado);
        URI url = uriComponentsBuilder.path("/buscar/{id}")
                .buildAndExpand(datosRespuestaEmpleado.id())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaEmpleado);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoEmpleado>> listarEmpleados() {
        Pageable paginacion = Pageable.unpaged();
        List<DatosListadoEmpleado> empleados = empleadoService.getAllEmpleados(paginacion).getContent();
        return ResponseEntity.ok(empleados);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaEmpleado> actualizarEmpleado(
            @Valid @RequestBody DatosActualizarEmpleado datosActualizarEmpleado) {
        DatosRespuestaEmpleado datosRespuestaEmpleado = empleadoService.updateEmpleado(datosActualizarEmpleado);
        return ResponseEntity.ok(datosRespuestaEmpleado);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarEmpleado(@PathVariable Long id) {
        try {
            DatosRespuestaEmpleado empleado = empleadoService.getEmpleadoById(id);
            return ResponseEntity.ok(empleado);
        } catch (Exception e) {
            String mensajeError = "Error al obtener el Empleado con ID " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<String> activarEmpleado(@PathVariable Long id) {
        empleadoService.activarEmpleado(id);
        return ResponseEntity.ok("Empleado activado correctamente");
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<String> desactivarEmpleado(@PathVariable Long id) {
        empleadoService.desactivarEmpleado(id);
        return ResponseEntity.ok("Empleado desactivado correctamente");
    }
}

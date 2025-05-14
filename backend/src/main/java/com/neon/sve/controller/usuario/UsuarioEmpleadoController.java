package com.neon.sve.controller.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neon.sve.dto.login.DatosRespuestaMensaje;
import com.neon.sve.dto.usuarioEmpleado.DatosListadoUsuarioEmpleado;
import com.neon.sve.dto.usuarioEmpleado.DatosRespuestaUsuarioEmpleado;
import com.neon.sve.service.usuarioEmpleado.UsuarioEmpleadoService;

@Controller
@RequestMapping("/usuario-empleado")
public class UsuarioEmpleadoController {

    @Autowired
    private UsuarioEmpleadoService usuarioEmpleadoService;

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoUsuarioEmpleado>> listarUsuarioEmpleados() {
        Pageable paginacion = Pageable.unpaged();
        List<DatosListadoUsuarioEmpleado> usuarioEmpleados = usuarioEmpleadoService.getAllUsuarioEmpleados(paginacion).getContent();
        return ResponseEntity.ok(usuarioEmpleados);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<DatosRespuestaMensaje> activarUsuarioEmpleado(@PathVariable Long id) {
        usuarioEmpleadoService.activarUsuarioEmpleado(id);
        return ResponseEntity.ok(new DatosRespuestaMensaje("Usuario empleado activado correctamente"));
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<DatosRespuestaMensaje> desactivarUsuarioEmpleado(@PathVariable Long id) {
        usuarioEmpleadoService.desactivarUsuarioEmpleado(id);
        return ResponseEntity.ok(new DatosRespuestaMensaje("Usuario empleado desactivado correctamente"));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarUsuarioEmpleado(@PathVariable Long id) {
        try {
            DatosRespuestaUsuarioEmpleado usuarioEmpleado = usuarioEmpleadoService.getUsuarioEmpleadoById(id);
            return ResponseEntity.ok(usuarioEmpleado);
        } catch (Exception e) {
            String mensajeError = "Error al obtener al usuario empleado con ID " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
}

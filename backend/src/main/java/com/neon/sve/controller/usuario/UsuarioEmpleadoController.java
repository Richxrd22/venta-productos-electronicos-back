package com.neon.sve.controller.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neon.sve.dto.usuarioEmpleado.DatosListadoUsuarioEmpleado;
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
}

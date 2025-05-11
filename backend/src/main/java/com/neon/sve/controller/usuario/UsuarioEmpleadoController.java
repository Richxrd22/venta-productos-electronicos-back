package com.neon.sve.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Page<DatosListadoUsuarioEmpleado>> listarUsuarioEmpleados(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable paginacion) {
        Page<DatosListadoUsuarioEmpleado> usuarioEmpleadoPage = usuarioEmpleadoService
                .getAllUsuarioEmpleados(paginacion);
        return ResponseEntity.ok(usuarioEmpleadoPage);
    }
}

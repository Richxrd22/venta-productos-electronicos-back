package com.neon.sve.controller.Usuario;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.data.domain.Sort;

import com.neon.sve.dto.usuario.DatosActualizarUsuario;
import com.neon.sve.dto.usuario.DatosListadoUsuario;
import com.neon.sve.dto.usuario.DatosRegistroUsuario;
import com.neon.sve.dto.usuario.DatosRespuestaUsuario;
import com.neon.sve.service.usuario.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(
            @Valid @RequestBody DatosRegistroUsuario datosRegistroUsuario,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaUsuario datosRespuestaUsuario = usuarioService.createUsuario(datosRegistroUsuario);
        URI url = uriComponentsBuilder.path("/buscar/{id_usuario}")
                .buildAndExpand(datosRespuestaUsuario.id_usuario())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoUsuario>> listarUsuarios(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable paginacion) {
        Page<DatosListadoUsuario> usuarioPage = usuarioService.getAllUsuarios(paginacion);
        return ResponseEntity.ok(usuarioPage);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(
            @Valid @RequestBody DatosActualizarUsuario datosActualizarUsuario) {
        DatosRespuestaUsuario datosRespuestaUsuario = usuarioService.updateUsuario(datosActualizarUsuario);
        return ResponseEntity.ok(datosRespuestaUsuario);
    }

    @GetMapping("/buscar/{id_usuario}")
    public ResponseEntity<?> buscarUsuario(@PathVariable Long id_usuario) {
        try {
            DatosRespuestaUsuario usuario = usuarioService.getUsuarioById(id_usuario);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            String mensajeError = "Error al obtener el Usuario con ID " + id_usuario;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

}

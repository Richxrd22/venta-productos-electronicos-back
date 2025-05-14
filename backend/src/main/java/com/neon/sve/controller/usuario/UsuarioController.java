package com.neon.sve.controller.usuario;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.neon.sve.dto.usuario.DatosActualizarUsuario;
import com.neon.sve.dto.usuario.DatosListadoUsuario;
import com.neon.sve.dto.usuario.DatosRegistroUsuario;
import com.neon.sve.dto.usuario.DatosRespuestaUsuario;
import com.neon.sve.dto.usuarioEmpleado.DatosUsuarioEmpleadoInfo;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.repository.UsuarioRepository;
import com.neon.sve.service.usuario.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(
            @Valid @RequestBody DatosRegistroUsuario datosRegistroUsuario,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaUsuario datosRespuestaUsuario = usuarioService.createUsuario(datosRegistroUsuario);
        URI url = uriComponentsBuilder.path("/buscar/{id_usuario}")
                .buildAndExpand(datosRespuestaUsuario.id())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoUsuario>> listarUsuarios() {
        Pageable paginacion = Pageable.unpaged();
        List<DatosListadoUsuario> usuarios = usuarioService.getAllUsuarios(paginacion).getContent();
        return ResponseEntity.ok(usuarios);
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

    @GetMapping("/info")
    public DatosUsuarioEmpleadoInfo obtenerDatosUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correo = authentication.getName();

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new DatosUsuarioEmpleadoInfo(
                usuario.getId(),
                usuario.getId_empleado().getNombre(),
                usuario.getId_empleado().getApellido(),
                usuario.getCorreo(),
                usuario.getId_rol().getNombre(),
                usuario.getClave_cambiada(),
                usuario.getActivo());
    }

}

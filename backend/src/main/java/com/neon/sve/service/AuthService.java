package com.neon.sve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.MensajeRespuesta;
import com.neon.sve.dto.login.DatosCambiarClave;
import com.neon.sve.dto.login.DatosLoginUsuario;
import com.neon.sve.dto.login.DatosRespuestaLoginUsuario;
import com.neon.sve.dto.login.DatosRespuestaMensaje;
import com.neon.sve.dto.usuarioempleado.DatosRegistroUsuarioEmpleado;
import com.neon.sve.jwt.JwtService;
import com.neon.sve.model.usuario.Empleado;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.repository.EmpleadoRepository;
import com.neon.sve.repository.UsuarioRepository;
import com.neon.sve.service.usuarioempleado.UsuarioEmpleadoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioEmpleadoService usuarioEmpleadoService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DatosRespuestaLoginUsuario login(DatosLoginUsuario request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.correo(), request.clave()));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos");
        }

        Empleado empleado = empleadoRepository.findByUsuarioCorreo(request.correo())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos"));

        if (!empleado.getActivo()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El usuario no está activo");
        }

        UserDetails usuarioDetails = usuarioRepository.findByCorreo(request.correo())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos"));

        Usuario usuario = usuarioRepository.findByCorreo(request.correo())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos"));

        String token = jwtService.getToken(usuarioDetails);

        return new DatosRespuestaLoginUsuario(token, usuario.getClave_cambiada());
    }

    public DatosRespuestaMensaje register(DatosRegistroUsuarioEmpleado request) {
        return usuarioEmpleadoService.createUsuarioEmpleado(request);
    }

    @Transactional
    public ResponseEntity<MensajeRespuesta> cambiarClaveUsuarioAutenticado(DatosCambiarClave request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correo = authentication.getName();

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.clave_actual(), usuario.getClave())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La clave actual no es correcta");
        }

        usuario.setClave(passwordEncoder.encode(request.clave_nueva()));
        usuario.setClave_cambiada(true);

        usuarioRepository.save(usuario);

        return ResponseEntity.ok(new MensajeRespuesta("Clave actualizada correctamente"));
    }

}

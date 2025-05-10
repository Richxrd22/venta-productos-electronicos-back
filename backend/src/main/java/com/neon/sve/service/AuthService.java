package com.neon.sve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.login.DatosCambiarClave;
import com.neon.sve.dto.login.DatosLoginUsuario;
import com.neon.sve.dto.login.DatosRespuestaLoginUsuario;
import com.neon.sve.dto.login.DatosRespuestaMensaje;
import com.neon.sve.dto.usuarioempleado.DatosRegistroUsuarioEmpleado;
import com.neon.sve.jwt.JwtService;
import com.neon.sve.model.Usuario.Empleado;
import com.neon.sve.model.Usuario.Usuario;
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
            // Esto lanza un BadCredentialsException si las credenciales no coinciden
            throw new BadCredentialsException("Correo o contraseña incorrectos");
        }

        Empleado empleado = empleadoRepository.findByUsuarioCorreo(request.correo())
                .orElseThrow(() -> new BadCredentialsException("Correo o contraseña incorrectos"));

        if (!empleado.getActivo()) {
            throw new DisabledException("El usuario no está activo");
        }

        UserDetails usuarioDetails = usuarioRepository.findByCorreo(request.correo())
                .orElseThrow();
        
        Usuario usuario = usuarioRepository.findByCorreo(request.correo()).orElseThrow();

        String token = jwtService.getToken(usuarioDetails);

        return new DatosRespuestaLoginUsuario(token,usuario.getClave_cambiada());
    }

    public DatosRespuestaMensaje register(DatosRegistroUsuarioEmpleado request) {
        return usuarioEmpleadoService.createUsuarioEmpleado(request);
    }

    @Transactional
public String cambiarClaveUsuarioAutenticado(DatosCambiarClave request) {
    Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
    String correo = authentication.getName();

    Usuario usuario = usuarioRepository.findByCorreo(correo)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    if (!passwordEncoder.matches(request.clave_actual(), usuario.getClave())) {
        throw new RuntimeException("La clave actual no es correcta");
    }

    usuario.setClave(passwordEncoder.encode(request.clave_nueva()));
    usuario.setClave_cambiada(true);

    usuarioRepository.save(usuario);

    return "Clave actualizada correctamente";
}

}

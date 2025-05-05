package com.neon.sve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.login.DatosLoginUsuario;
import com.neon.sve.dto.login.DatosRespuestaLoginUsuario;
import com.neon.sve.dto.usuarioempleado.DatosRegistroUsuarioEmpleado;
import com.neon.sve.jwt.JwtService;
import com.neon.sve.model.Usuario.Empleado;
import com.neon.sve.repository.EmpleadoRepository;
import com.neon.sve.repository.UsuarioRepository;
import com.neon.sve.service.usuarioempleado.UsuarioEmpleadoService;

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

    public DatosRespuestaLoginUsuario login(DatosLoginUsuario request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.correo_usuario(), request.clave()));

        UserDetails usuario = usuarioRepository.findByCorreo(request.correo_usuario()).orElseThrow();

        Empleado empleado = empleadoRepository.findByUsuarioCorreo(request.correo_usuario()).orElseThrow();
        if (!empleado.getActivo()) {
            throw new RuntimeException("El usuario no está activo.");
        }

        String token = jwtService.getToken(usuario);

        return new DatosRespuestaLoginUsuario(token);
    }

    public DatosRespuestaLoginUsuario register(DatosRegistroUsuarioEmpleado request) {
        return usuarioEmpleadoService.createUsuarioEmpleado(request);
    }

    
}

package com.neon.sve.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.MensajeRespuesta;
import com.neon.sve.dto.login.DatosCambiarClave;
import com.neon.sve.dto.login.DatosLoginUsuario;
import com.neon.sve.dto.login.DatosRespuestaLoginUsuario;
import com.neon.sve.dto.login.DatosRespuestaMensaje;
import com.neon.sve.dto.usuarioempleado.DatosRegistroUsuarioEmpleado;
import com.neon.sve.jwt.JwtService;
import com.neon.sve.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/autenticacion")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<DatosRespuestaLoginUsuario> login(@Valid @RequestBody DatosLoginUsuario request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/registro")
    public ResponseEntity<?> register(@Valid @RequestBody DatosRegistroUsuarioEmpleado request) {
        try {
            DatosRespuestaMensaje respuesta = authService.register(request);
            return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error interno del servidor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/validar-token")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token no proporcionado");
        }

        try {
            String jwtToken = token.substring(7);
            String correo = jwtService.getCorreoFromToken(jwtToken);

            if (correo == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido");
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(correo);

            if (!jwtService.isTokenValid(jwtToken, userDetails)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido o expirado");
            }

            return ResponseEntity.ok("Token válido");

        } catch (ResponseStatusException ex) {
            throw ex; 
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido");
        }
    }

    @PutMapping("/cambiar-clave")
    public ResponseEntity<MensajeRespuesta> cambiarClave(@Valid @RequestBody DatosCambiarClave request) {
        return authService.cambiarClaveUsuarioAutenticado(request);
    }

}

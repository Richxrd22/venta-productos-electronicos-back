package com.neon.sve.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Mensaje por defecto
        String errorMessage = "{\"error\": \"Error de autenticación\"}";
        int status = HttpServletResponse.SC_UNAUTHORIZED;

        if (authException.getClass().getSimpleName().equals("BadCredentialsException")) {
            errorMessage = "{\"error\": \"Correo o contraseña incorrectos\"}";
        } else if (authException.getClass().getSimpleName().equals("DisabledException")) {
            errorMessage = "{\"error\": \"El usuario no está activo\"}";
            status = HttpServletResponse.SC_FORBIDDEN; // 403
        }

        response.setStatus(status);
        response.getWriter().write(errorMessage);
    }
}

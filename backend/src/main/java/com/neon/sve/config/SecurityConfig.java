package com.neon.sve.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.neon.sve.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:3000"));
                    config.setAllowedMethods(List.of("GET", "PUT", "DELETE", "POST"));
                    config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest -> authRequest
                        // AUTENTICACIÓN
                        .requestMatchers("/autenticacion/login").permitAll()
                        .requestMatchers("/autenticacion/registro").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers("/autenticacion/validar-token", "/autenticacion/cambiar-clave")
                        .hasAnyRole("ADMINISTRADOR", "ALMACENISTA", "VENDEDOR")
                        // USUARIO INFO
                        .requestMatchers("/usuario/info").hasAnyRole("ADMINISTRADOR",
                                "ALMACENISTA", "VENDEDOR")
                        // --- ACCESO GENERAL PARA TODOS LOS ROLES AUTENTICADOS (listar / buscar) ---
                        .requestMatchers(
                                "/rol/listar", "/rol/buscar/**",
                                "/empleado/listar", "/empleado/buscar/**",
                                "/usuario/listar", "/usuario/buscar/**",
                                "/usuario-empleado/listar", "/usuario-empleado/buscar/**",
                                "/producto/listar", "/producto/buscar/**",
                                "/marca/listar", "/marca/buscar/**",
                                "/categoria/listar", "/categoria/buscar/**",
                                "/categoria/listar-detalle",
                                "/categoria/listar/nivel/**",
                                "/cliente/listar",
                                "/cliente/buscar/**",
                                "/proveedor/listar", "/proveedor/buscar/**",
                                "/ingresoStock/listar", "/ingresoStock/buscar/**",
                                "/serie-productos/listar", "/serie-productos/buscar/**",
                                "/serie-producto/listar/detalle/**",
                                "/devolucion-producto/listar", "/devolucion-producto/buscar/**",
                                "/garantia/listar", "/garantia/buscar/**",
                                "/reclamo-garantia/listar", "/reclamo-garantia/buscar/**",
                                "/descuento/listar",
                                "/descuento/buscar/**",
                                "/cupon/listar",
                                "/cupon/buscar/**",
                                "/devolucion-venta/listar",
                                "/devolucion-venta/buscar/**",
                                "/metodo-pago/listar",
                                "/metodo-pago/buscar/**",
                                "/venta/listar",
                                "/venta/buscar/**")
                        .hasAnyRole("ADMINISTRADOR", "ALMACENISTA", "VENDEDOR")
                        // --- SOLO ADMINISTRADOR ---
                        .requestMatchers(
                                "/rol/registrar", "/rol/actualizar", "/rol/eliminar/**",
                                "/empleado/actualizar",
                                "/usuario/actualizar",
                                "/usuario-empleado/actualizar", "/usuario-empleado/activar/**",
                                "/usuario-empleado/desactivar/**",
                                "/cupon/registrar",
                                "/cupon/actualizar",
                                "/cupon/activar/**",
                                "/cupon/desactivar/**",
                                "/reclamo-garantia/activar/**",
                                "/reclamo-garantia/desactivar/**",
                                "/devolucion-producto/registrar", "/devolucion-producto/actualizar",
                                "/devolucion-producto/activar/**",
                                "/devolucion-producto/desactivar/**")
                        .hasRole("ADMINISTRADOR")
                        // --- ADMINISTRADOR Y ALMACENISTA ---
                        .requestMatchers(
                                "/producto/registrar", "/producto/actualizar", "/producto/activar/**",
                                "/producto/desactivar/**",
                                "/marca/registrar", "/marca/actualizar", "/marca/activar/**", "/marca/desactivar/**",
                                "/categoria/registrar", "/categoria/actualizar", "/categoria/activar/**",
                                "/categoria/desactivar/**",
                                "/proveedor/registrar", "/proveedor/actualizar", "/proveedor/activar/**",
                                "/proveedor/desactivar/**",
                                "/ingresoStock/registrar", "/ingresoStock/actualizar", "/ingresoStock/activar/**",
                                "/ingresoStock/desactivar/**",
                                "/serie-productos/registrar", "/serie-productos/actualizar")
                        .hasAnyRole("ADMINISTRADOR", "ALMACENISTA")

                        // --- ADMINISTRADOR Y VENDEDOR ---
                        .requestMatchers(
                                "/cliente/registrar",
                                "/cliente/actualizar",
                                "/cliente/activar/**",
                                "/cliente/desactivar/**",
                                "/cupon/incrementar-uso/**",
                                "/devolucion-venta/registrar",
                                "/devolucion-venta/actualizar",
                                "/metodo-pago/registrar",
                                "/metodo-pago/actualizar",
                                "/reclamo-garantia/registrar",
                                "/reclamo-garantia/actualizar",
                                "/venta/registrar",
                                "/venta/actualizar",
                                "/metodo-pago/activar/**",
                                "/metodo-pago/desactivar/**",
                                "/venta/activar/**",
                                "/venta/cancelar/**",
                                "/garantia/registrar", "/garantia/actualizar",
                                "/garantia/activar/**", "/garantia/desactivar/**")
                        .hasAnyRole("ADMINISTRADOR", "VENDEDOR")
                        .anyRequest().authenticated())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler))
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}

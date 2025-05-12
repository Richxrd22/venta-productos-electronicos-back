package com.neon.sve.config.userInitializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class ApplicationService {
     @Autowired
    private CreacionUsuarioService creacionUsuarioService;

    @PostConstruct
    public void init() {
        creacionUsuarioService.crearUsuarioEmpleadoRol();
    }
}

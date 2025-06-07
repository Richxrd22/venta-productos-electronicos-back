package com.neon.sve.dto.annotations.usuario.unique.correo;

import org.springframework.beans.factory.annotation.Autowired;

import com.neon.sve.repository.EmpleadoRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueCorreoValidator implements ConstraintValidator<UniqueCorreo, String> {
   
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
    
        if (value == null || value.isEmpty()) {
            return true; // Si el valor es nulo o vacío, consideramos que es válido.
        }

        return empleadoRepository.findByUsuarioCorreo(value).isEmpty();
    }

}

package com.neon.sve.dto.annotations.usuario.unique.dni;

import org.springframework.beans.factory.annotation.Autowired;

import com.neon.sve.repository.EmpleadoRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueDniValidator implements ConstraintValidator<UniqueDni,String> {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null || value.isEmpty()) {
                return true; // No validation for null or empty values
                
            }
            return empleadoRepository.findByDni(value).isEmpty();
    }
    
}

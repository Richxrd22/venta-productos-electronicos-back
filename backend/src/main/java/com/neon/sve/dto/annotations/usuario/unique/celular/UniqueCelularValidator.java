package com.neon.sve.dto.annotations.usuario.unique.celular;

import org.springframework.beans.factory.annotation.Autowired;

import com.neon.sve.repository.EmpleadoRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueCelularValidator implements ConstraintValidator<UniqueCelular, String> {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return empleadoRepository.findByCelular(value).isEmpty();
    }
    
}

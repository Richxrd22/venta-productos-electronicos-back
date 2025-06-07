package com.neon.sve.dto.annotations.usuario.unique.dni;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDniValidator.class)
@Documented
public @interface UniqueDni {
    
    String message() default "El DNI ya está en uso por otro empleado";
 
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}

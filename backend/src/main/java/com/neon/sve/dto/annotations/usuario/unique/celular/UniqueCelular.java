package com.neon.sve.dto.annotations.usuario.unique.celular;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCelularValidator.class)
@Documented
public @interface UniqueCelular {
    String message() default "El celular ya existe en un empleado registrado";
 
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}

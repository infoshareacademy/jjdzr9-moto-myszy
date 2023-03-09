package com.isa.jjdzr.walletweb;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =  ValidSymbolsValidator.class)
public @interface ValidSymbols {
    String message() default "Invalid symbols";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

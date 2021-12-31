package com.example.demo.book.Validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface IsNumber{
    public String value() default "^[0-9]*$";
    public String message() default "Price is invalid(only numbers)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
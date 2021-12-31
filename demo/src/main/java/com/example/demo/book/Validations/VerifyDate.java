package com.example.demo.book.Validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = VerifyDateValid.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface VerifyDate{
    public String value() default "^[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}$";
    public String message() default "Date is invalid(dd-mm-yyyy)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
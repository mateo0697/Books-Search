package com.example.demo.book.Validations;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsNumberValidator implements
        ConstraintValidator<IsNumber, String> {


    @Override
    public void initialize(IsNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        regex=constraintAnnotation.value();
    }


    @Override
    public boolean isValid(String price, ConstraintValidatorContext constraintValidatorContext) {
        if (price == null){
            return true;
        }
        Pattern pat = Pattern.compile(regex);
        Matcher test = pat.matcher(price);
        return test.find();
    }

    private String regex;
}

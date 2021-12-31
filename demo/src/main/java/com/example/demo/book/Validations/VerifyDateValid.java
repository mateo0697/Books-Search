package com.example.demo.book.Validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyDateValid implements
        ConstraintValidator<VerifyDate, String>{

    @Override
    public void initialize(VerifyDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        regex=constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null){
            return false;
        }
        Pattern pat = Pattern.compile(regex);
        Matcher test = pat.matcher(date);
        if(test.find()){
            String[] parts = date.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            return ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day <= 31) || ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30) || (month == 2 && year % 4 == 0 && day <= 29) || (month == 2 && year % 4 != 0 && day <= 28);

//      (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) month with 31 days
//
//      (month == 4 || month == 6 || month == 9 || month == 11 ) && day <= 30 ) month with 30 days
//
//      (month == 2 && year%4 == 0 && day <=29) binary year
//
//      (month == 2 && year%4 != 0 && day <=29) non binary year
        }else{
            return false;
        }
    }

    private String regex;
}


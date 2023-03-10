package com.isa.jjdzr.walletweb;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidSymbolsValidator implements ConstraintValidator<ValidSymbols, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("[^A-z0-9]");
        Matcher matcher = pattern.matcher(value);
        boolean badCharacters = matcher.find();
        return !badCharacters;
    }
}

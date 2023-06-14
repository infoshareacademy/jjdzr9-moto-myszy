package com.isa.jjdzr.walletweb.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class UserTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testValidUser() {
        //Given
        User user = new User();
        user.setUsername("johnDoe");
        user.setPassword("password123");
        user.setConfirmPassword("password123");
        //When
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        //Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidUsername() {
        User user = new User();
        user.setUsername("j");
        user.setPassword("password123");
        user.setConfirmPassword("password123");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<User> violation = violations.iterator().next();
        Assertions.assertEquals("Nazwa użytkownika jest zbyt krótka", violation.getMessage());
    }

    @Test
    public void testInvalidPassword() {
        User user = new User();
        user.setUsername("johnDoe");
        user.setPassword("pass");
        user.setConfirmPassword("pass");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<User> violation = violations.iterator().next();
        Assertions.assertEquals("Hasło jest za krótkie", violation.getMessage());
    }

}

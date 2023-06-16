package com.isa.jjdzr.walletweb.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class UserDtoTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testValidUser() {
        //Given
        UserDto user = new UserDto();
        user.setUsername("johnDoe");
        user.setPassword("password123");
        user.setConfirmPassword("password123");
        //When
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        //Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidUsername() {
        //Given
        UserDto user = new UserDto();
        user.setUsername("j");
        user.setPassword("password123");
        user.setConfirmPassword("password123");
        //When
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        //Then
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        Assertions.assertEquals("Nazwa użytkownika jest zbyt krótka", violation.getMessage());
    }

    @Test
    void testInvalidPassword() {
        //Given
        UserDto user = new UserDto();
        user.setUsername("johnDoe");
        user.setPassword("pass");
        user.setConfirmPassword("pass");
        //When
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        //Then
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<UserDto> violation = violations.iterator().next();
        Assertions.assertEquals("Hasło jest za krótkie", violation.getMessage());
    }

}

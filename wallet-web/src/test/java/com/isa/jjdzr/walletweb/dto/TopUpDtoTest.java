package com.isa.jjdzr.walletweb.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

public class TopUpDtoTest {
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    public void testValidTopUpDto() {
        // Given
        BigDecimal cash = new BigDecimal("500");

        TopUpDto topUpDto = new TopUpDto();
        topUpDto.setCash(cash);

        // When
        Set<ConstraintViolation<TopUpDto>> violations = validator.validate(topUpDto);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testNegativeCashValue() {
        // Given
        BigDecimal cash = new BigDecimal("-100");

        TopUpDto topUpDto = new TopUpDto();
        topUpDto.setCash(cash);

        // When
        Set<ConstraintViolation<TopUpDto>> violations = validator.validate(topUpDto);

        // Then
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<TopUpDto> violation = violations.iterator().next();
        Assertions.assertEquals("Wartość nie może być ujemna", violation.getMessage());
        Assertions.assertEquals("cash", violation.getPropertyPath().toString());
    }

    @Test
    public void testExcessiveCashValue() {
        // Given
        BigDecimal cash = new BigDecimal("2000000");

        TopUpDto topUpDto = new TopUpDto();
        topUpDto.setCash(cash);

        // When
        Set<ConstraintViolation<TopUpDto>> violations = validator.validate(topUpDto);

        // Then
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<TopUpDto> violation = violations.iterator().next();
        Assertions.assertEquals("Zbyt duża wartość", violation.getMessage());
        Assertions.assertEquals("cash", violation.getPropertyPath().toString());
    }

    @Test
    public void testNullCashValue() {
        // Given
        TopUpDto topUpDto = new TopUpDto();
        topUpDto.setCash(null);

        // When
        Set<ConstraintViolation<TopUpDto>> violations = validator.validate(topUpDto);

        // Then
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<TopUpDto> violation = violations.iterator().next();
        Assertions.assertEquals("Pole nie może być puste", violation.getMessage());
        Assertions.assertEquals("cash", violation.getPropertyPath().toString());
    }

    @Test
    public void testMissingCashValue() {
        // Given
        TopUpDto topUpDto = new TopUpDto();

        // When
        Set<ConstraintViolation<TopUpDto>> violations = validator.validate(topUpDto);

        // Then
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<TopUpDto> violation = violations.iterator().next();
        Assertions.assertEquals("Pole nie może być puste", violation.getMessage());
        Assertions.assertEquals("cash", violation.getPropertyPath().toString());
    }
}

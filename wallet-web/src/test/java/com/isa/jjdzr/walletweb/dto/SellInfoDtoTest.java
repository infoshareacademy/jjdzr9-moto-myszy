package com.isa.jjdzr.walletweb.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

public class SellInfoDtoTest {
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    public void testValidSellInfoDto() {
        // Given
        Long walletAssetId = 1L;
        BigDecimal quantityToSell = new BigDecimal("5");

        SellInfoDto sellInfoDto = new SellInfoDto();
        sellInfoDto.setWalletAssetId(walletAssetId);
        sellInfoDto.setQuantityToSell(quantityToSell);

        // When
        Set<ConstraintViolation<SellInfoDto>> violations = validator.validate(sellInfoDto);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidSellInfoDto() {
        // Given
        Long walletAssetId = 1L;
        BigDecimal quantityToSell = new BigDecimal("-10");

        SellInfoDto sellInfoDto = new SellInfoDto();
        sellInfoDto.setWalletAssetId(walletAssetId);
        sellInfoDto.setQuantityToSell(quantityToSell);

        // When
        Set<ConstraintViolation<SellInfoDto>> violations = validator.validate(sellInfoDto);

        // Then
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<SellInfoDto> violation = violations.iterator().next();
        Assertions.assertEquals("must be greater than or equal to 0", violation.getMessage());
        Assertions.assertEquals("quantityToSell", violation.getPropertyPath().toString());
    }

    @Test
    public void testNegativeQuantityToSell() {
        // Given
        Long walletAssetId = 1L;

        SellInfoDto sellInfoDto = new SellInfoDto();
        sellInfoDto.setWalletAssetId(walletAssetId);
        sellInfoDto.setQuantityToSell(new BigDecimal("-10"));

        // When
        Set<ConstraintViolation<SellInfoDto>> violations = validator.validate(sellInfoDto);

        // Then
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<SellInfoDto> violation = violations.iterator().next();
        Assertions.assertEquals("must be greater than or equal to 0", violation.getMessage());
        Assertions.assertEquals("quantityToSell", violation.getPropertyPath().toString());
    }

    @Test
    public void testMissingQuantityToSell() {
        // Arrange
        Long walletAssetId = 1L;

        SellInfoDto sellInfoDto = new SellInfoDto();
        sellInfoDto.setWalletAssetId(walletAssetId);

        // Act
        Set<ConstraintViolation<SellInfoDto>> violations = validator.validate(sellInfoDto);

        // Assert
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<SellInfoDto> violation = violations.iterator().next();
        Assertions.assertEquals("must not be null", violation.getMessage());
        Assertions.assertEquals("quantityToSell", violation.getPropertyPath().toString());
    }

}

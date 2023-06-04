package com.isa.jjdzr.walletweb.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class DetailedWalletAssetDtoTest {
    @Test
    public void testConstructorAndGetters() {
        // GIVEN
        Long id = 1L;
        Long walletId = 2L;
        String assetId = "ABC123";
        String assetName = "Example Asset";
        BigDecimal purchasePrice = new BigDecimal("10.50");
        BigDecimal currentPrice = new BigDecimal("12.75");
        BigDecimal quantity = new BigDecimal("5");
        BigDecimal purchaseValue = new BigDecimal("52.50");
        BigDecimal currentValue = new BigDecimal("63.75");
        BigDecimal profit = new BigDecimal("11.25");

        // WHEN
        DetailedWalletAssetDto dto = new DetailedWalletAssetDto(
                id, walletId, assetId, assetName, purchasePrice, currentPrice,
                quantity, purchaseValue, currentValue, profit
        );

        // THEN
        Assertions.assertEquals(id, dto.getId());
        Assertions.assertEquals(walletId, dto.getWalletId());
        Assertions.assertEquals(assetId, dto.getAssetId());
        Assertions.assertEquals(assetName, dto.getAssetName());
        Assertions.assertEquals(purchasePrice, dto.getPurchasePrice());
        Assertions.assertEquals(currentPrice, dto.getCurrentPrice());
        Assertions.assertEquals(quantity, dto.getQuantity());
        Assertions.assertEquals(purchaseValue, dto.getPurchaseValue());
        Assertions.assertEquals(currentValue, dto.getCurrentValue());
        Assertions.assertEquals(profit, dto.getProfit());
    }

    @Test
    public void testSetters() {
        // GIVRN
        DetailedWalletAssetDto dto = new DetailedWalletAssetDto();
        Long id = 1L;
        Long walletId = 2L;
        String assetId = "ABC123";
        String assetName = "Example Asset";
        BigDecimal purchasePrice = new BigDecimal("10.50");
        BigDecimal currentPrice = new BigDecimal("12.75");
        BigDecimal quantity = new BigDecimal("5");
        BigDecimal purchaseValue = new BigDecimal("52.50");
        BigDecimal currentValue = new BigDecimal("63.75");
        BigDecimal profit = new BigDecimal("11.25");

        // WHEN
        dto.setId(id);
        dto.setWalletId(walletId);
        dto.setAssetId(assetId);
        dto.setAssetName(assetName);
        dto.setPurchasePrice(purchasePrice);
        dto.setCurrentPrice(currentPrice);
        dto.setQuantity(quantity);
        dto.setPurchaseValue(purchaseValue);
        dto.setCurrentValue(currentValue);
        dto.setProfit(profit);

        // THEN
        Assertions.assertEquals(id, dto.getId());
        Assertions.assertEquals(walletId, dto.getWalletId());
        Assertions.assertEquals(assetId, dto.getAssetId());
        Assertions.assertEquals(assetName, dto.getAssetName());
        Assertions.assertEquals(purchasePrice, dto.getPurchasePrice());
        Assertions.assertEquals(currentPrice, dto.getCurrentPrice());
        Assertions.assertEquals(quantity, dto.getQuantity());
        Assertions.assertEquals(purchaseValue, dto.getPurchaseValue());
        Assertions.assertEquals(currentValue, dto.getCurrentValue());
        Assertions.assertEquals(profit, dto.getProfit());
    }
}

package com.isa.jjdzr.walletweb.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class WalletAssetEntityTest {
    private WalletAssetEntity walletAssetEntity;

    @BeforeEach
    public void setUp() {
        walletAssetEntity = new WalletAssetEntity();
    }

    @Test
    void testSetId() {
        Long id = 1L;
        walletAssetEntity.setId(id);
        Assertions.assertEquals(id, walletAssetEntity.getId());
    }

    @Test
    void testSetAssetId() {
        String assetId = "assetId";
        walletAssetEntity.setAssetId(assetId);
        Assertions.assertEquals(assetId, walletAssetEntity.getAssetId());
    }

    @Test
    void testSetAssetName() {
        String assetName = "assetName";
        walletAssetEntity.setAssetName(assetName);
        Assertions.assertEquals(assetName, walletAssetEntity.getAssetName());
    }

    @Test
    void testSetPurchasePrice() {
        BigDecimal purchasePrice = BigDecimal.valueOf(100.00);
        walletAssetEntity.setPurchasePrice(purchasePrice);
        Assertions.assertEquals(purchasePrice, walletAssetEntity.getPurchasePrice());
    }

    @Test
    void testSetCurrentPrice() {
        BigDecimal currentPrice = BigDecimal.valueOf(200.00);
        walletAssetEntity.setCurrentPrice(currentPrice);
        Assertions.assertEquals(currentPrice, walletAssetEntity.getCurrentPrice());
    }

    @Test
    void testSetQuantity() {
        BigDecimal quantity = BigDecimal.valueOf(10.00);
        walletAssetEntity.setQuantity(quantity);
        Assertions.assertEquals(quantity, walletAssetEntity.getQuantity());
    }

    @Test
    void testSetWalletEntity() {
        WalletEntity walletEntity = new WalletEntity();
        walletAssetEntity.setWalletEntity(walletEntity);
        Assertions.assertNotNull(walletAssetEntity.getWalletEntity());
        Assertions.assertEquals(walletEntity, walletAssetEntity.getWalletEntity());
    }
}

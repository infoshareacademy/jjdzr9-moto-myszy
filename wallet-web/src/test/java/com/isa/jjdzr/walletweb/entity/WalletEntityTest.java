package com.isa.jjdzr.walletweb.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

class WalletEntityTest {
    private WalletEntity walletEntity;

    @BeforeEach
    void setUp() {
        walletEntity = new WalletEntity();
    }

    @Test
    void testSetAndGetId() {
        Long id = 1L;
        walletEntity.setId(id);

        Assertions.assertEquals(id, walletEntity.getId());
    }

    @Test
    void testSetAndGetWalletName() {
        String walletName = "testWallet";
        walletEntity.setWalletName(walletName);

        Assertions.assertEquals(walletName, walletEntity.getWalletName());
    }

    @Test
    void testSetAndGetCash() {
        BigDecimal cash = BigDecimal.valueOf(100);
        walletEntity.setCash(cash);

        Assertions.assertEquals(cash, walletEntity.getCash());
    }

    @Test
    void testSetAndGetUserEntity() {
        UserEntity userEntity = new UserEntity();
        walletEntity.setUserEntity(userEntity);

        Assertions.assertEquals(userEntity, walletEntity.getUserEntity());
    }

    @Test
    void testSetAndGetWalletAssetEntity() {
        WalletAssetEntity walletAssetEntity = new WalletAssetEntity();
        walletEntity.setWalletAssetEntity(Collections.singletonList(walletAssetEntity));

        Assertions.assertEquals(walletAssetEntity, walletEntity.getWalletAssetEntity().get(0));
    }
}

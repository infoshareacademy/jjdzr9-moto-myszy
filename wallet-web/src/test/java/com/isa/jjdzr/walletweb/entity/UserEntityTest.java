package com.isa.jjdzr.walletweb.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserEntityTest {

    private UserEntity userEntity;

    @BeforeEach
    public void setUp() {
        userEntity = new UserEntity();
    }

    @Test
    void testSetUsername() {
        String username = "testUsername";
        userEntity.setUsername(username);
        assertEquals(username, userEntity.getUsername());
    }

    @Test
    void testSetPassword() {
        String password = "testPassword";
        userEntity.setPassword(password);
        assertEquals(password, userEntity.getPassword());
    }

    @Test
    void testSetId() {
        Long id = 1L;
        userEntity.setId(id);
        assertEquals(id, userEntity.getId());
    }

    @Test
    void testSetWalletEntity() {
        List<WalletEntity> walletEntityList = new ArrayList<>();
        WalletEntity walletEntity = new WalletEntity();
        walletEntityList.add(walletEntity);

        userEntity.setWalletEntity(walletEntityList);
        Assertions.assertNotNull(userEntity.getWalletEntity());
        assertEquals(1, userEntity.getWalletEntity().size());
        assertEquals(walletEntity, userEntity.getWalletEntity().get(0));
    }

}
package com.isa.jjdzr.walletweb.dto;

import org.junit.Before;
import org.junit.Test;


import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.*;

public class BuyInfoDtoTest {
    @Test
    public void testSettersAndGetters() {
        // GIVEN
        BuyInfoDto buyInfoDto = new BuyInfoDto();
        String assetId = "ABC123";
        String assetName = "Example Asset";
        Long walletId = 12345L;
        BigDecimal price = new BigDecimal("9.99");

        // WHEN
        buyInfoDto.setAssetId(assetId);
        buyInfoDto.setAssetName(assetName);
        buyInfoDto.setWalletId(walletId);
        buyInfoDto.setPrice(price);

        // THEN
        assertEquals(assetId, buyInfoDto.getAssetId());
        assertEquals(assetName, buyInfoDto.getAssetName());
        assertEquals(walletId, buyInfoDto.getWalletId());
        assertEquals(price, buyInfoDto.getPrice());
    }

    @Test
    public void testEquals() {
        // GIVEN
        BuyInfoDto buyInfoDto1 = new BuyInfoDto();
        buyInfoDto1.setAssetId("ABC123");
        buyInfoDto1.setAssetName("Example Asset");
        buyInfoDto1.setWalletId(12345L);
        buyInfoDto1.setPrice(new BigDecimal("9.99"));

        BuyInfoDto buyInfoDto2 = new BuyInfoDto();
        buyInfoDto2.setAssetId("ABC123");
        buyInfoDto2.setAssetName("Example Asset");
        buyInfoDto2.setWalletId(12345L);
        buyInfoDto2.setPrice(new BigDecimal("9.99"));

        BuyInfoDto buyInfoDto3 = new BuyInfoDto();
        buyInfoDto3.setAssetId("XYZ789");
        buyInfoDto3.setAssetName("Another Asset");
        buyInfoDto3.setWalletId(54321L);
        buyInfoDto3.setPrice(new BigDecimal("19.99"));

        // WHEN & THEN
        assertTrue(buyInfoDto1.equals(buyInfoDto2));
        assertTrue(buyInfoDto2.equals(buyInfoDto1));
        assertFalse(buyInfoDto1.equals(buyInfoDto3));
        assertFalse(buyInfoDto3.equals(buyInfoDto1));
    }
}
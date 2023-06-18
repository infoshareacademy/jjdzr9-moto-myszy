package com.isa.jjdzr.walletweb.mapper;

import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletweb.entity.WalletEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class WalletMapperTest {

    @InjectMocks
    private WalletMapper mapper = WalletMapper.MAPPER;

    @Test
    void shouldMapDtoToEntity() {

        Wallet wallet = new Wallet();
        wallet.setWalletName("WalletName");
        wallet.setCash(BigDecimal.valueOf(200.00));

        WalletEntity result = mapper.dtoToEntity(wallet);

        assertEquals(wallet.getWalletName(), result.getWalletName());
        assertEquals(wallet.getCash(), result.getCash());
    }

    @Test
    void shouldMapEntityToDto() {

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setWalletName("WalletName");
        walletEntity.setCash(BigDecimal.valueOf(200.00));

        Wallet result = mapper.entityToDto(walletEntity);

        assertEquals(walletEntity.getWalletName(), result.getWalletName());
        assertEquals(walletEntity.getCash(), result.getCash());
    }
}

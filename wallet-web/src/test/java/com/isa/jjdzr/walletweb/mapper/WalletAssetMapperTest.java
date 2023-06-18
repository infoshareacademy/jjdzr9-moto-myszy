package com.isa.jjdzr.walletweb.mapper;

import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletweb.entity.WalletAssetEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class WalletAssetMapperTest {

    @InjectMocks
    private WalletAssetMapper mapper = WalletAssetMapper.MAPPER;

    @Test
    void shouldMapDtoToEntity() {

        WalletAsset walletAsset = new WalletAsset();
        walletAsset.setAssetName("AssetName");
        walletAsset.setCurrentPrice(BigDecimal.valueOf(100));

        WalletAssetEntity result = mapper.dtoToEntity(walletAsset);

        assertEquals(walletAsset.getAssetName(), result.getAssetName());
        assertEquals(walletAsset.getCurrentPrice(), result.getCurrentPrice());
    }

    @Test
    void shouldMapEntityToDto() {

        WalletAssetEntity walletAssetEntity = new WalletAssetEntity();
        walletAssetEntity.setAssetName("AssetName");
        walletAssetEntity.setCurrentPrice(BigDecimal.valueOf(100.00));

        WalletAsset result = mapper.entityToDto(walletAssetEntity);

        assertEquals(walletAssetEntity.getAssetName(), result.getAssetName());
        assertEquals(walletAssetEntity.getCurrentPrice(), result.getCurrentPrice());
    }
}


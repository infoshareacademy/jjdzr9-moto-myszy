package com.isa.jjdzr.walletweb.mapper;

import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletweb.entity.WalletAssetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletAssetMapper {

    WalletAssetMapper MAPPER = Mappers.getMapper(WalletAssetMapper.class);
    WalletAssetEntity dtoToEntity(WalletAsset walletAsset);
    WalletAsset entityToDto(WalletAssetEntity walletAssetEntity);
}

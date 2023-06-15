package com.isa.jjdzr.walletweb.mapper;

import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletweb.entity.WalletEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletMapper {

    WalletMapper MAPPER = Mappers.getMapper(WalletMapper.class);
    WalletEntity dtoToEntity(Wallet wallet);
    Wallet entityToDto(WalletEntity walletEntity);
}

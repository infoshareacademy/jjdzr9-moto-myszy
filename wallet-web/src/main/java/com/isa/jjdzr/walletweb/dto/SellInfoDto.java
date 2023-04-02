package com.isa.jjdzr.walletweb.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SellInfoDto {

    private Long walletAssetId;
    private BigDecimal quantityToSell;
}

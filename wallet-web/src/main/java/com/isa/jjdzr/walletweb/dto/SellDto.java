package com.isa.jjdzr.walletweb.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SellDto {

    private Long walletAssetId;
    private BigDecimal quantityToSell;
}

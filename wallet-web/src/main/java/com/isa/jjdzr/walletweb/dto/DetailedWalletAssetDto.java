package com.isa.jjdzr.walletweb.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetailedWalletAssetDto {
    private Long id;
    private Long walletId;
    private String assetId;
    private String assetName;
    private BigDecimal purchasePrice;
    private BigDecimal currentPrice;
    private BigDecimal quantity;
    private BigDecimal purchaseValue;
    private BigDecimal currentValue;
    private BigDecimal profit;
}

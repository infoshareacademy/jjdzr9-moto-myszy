package com.isa.jjdzr.dto;

import com.isa.jjdzr.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletAsset implements BaseEntity {
    private Long id;
    private Long walletId;
    private String assetId;
    private BigDecimal purchasePrice;
    private BigDecimal quantity;

}

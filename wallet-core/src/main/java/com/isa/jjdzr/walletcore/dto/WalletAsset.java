package com.isa.jjdzr.walletcore.dto;


import com.isa.jjdzr.walletcore.common.BaseEntity;
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
    private BigDecimal currentPrice;
    private BigDecimal quantity;

}

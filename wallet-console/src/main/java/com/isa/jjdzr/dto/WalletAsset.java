package com.isa.jjdzr.dto;

import com.isa.jjdzr.common.BaseEntity;
import com.isa.jjdzr.market.Market;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class WalletAsset implements BaseEntity {
    private Long id;
    private Long walletId;
    private String assetId;
    private BigDecimal purchasePrice;
    private BigDecimal quantity;

    //TODO: put in wallet service
    public BigDecimal getCurrentPrice(Market market) {
        List<Asset> assetList = market.availableAssets();
        for (Asset a : assetList) {
            if (a.getId().equals(this.assetId)) {
                return a.getCurrentPrice();
            }
        }
        return null;
    }
}

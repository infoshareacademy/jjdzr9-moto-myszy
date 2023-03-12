package com.isa.jjdzr.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.isa.jjdzr.common.BaseEntity;
import com.isa.jjdzr.market.Market;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class WalletAsset implements BaseEntity {
    private Long id;
    private final String assetId;
    private final BigDecimal purchasePrice;
    private BigDecimal purchasedQuantity;

    @JsonCreator
    public WalletAsset() {
        this.assetId = "id";
        this.purchasePrice = new BigDecimal("0");
        this.purchasedQuantity = new BigDecimal("0");
    }

    public WalletAsset(Asset asset, String purchasedQuantity) {
        this.assetId = asset.getId();
        this.purchasePrice = new BigDecimal(String.valueOf(asset.getCurrentPrice()));
        this.purchasedQuantity = new BigDecimal(purchasedQuantity);

    }

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

    public void reduceAsset(String quantity) {
        this.purchasedQuantity = purchasedQuantity.subtract(new BigDecimal(quantity));
    }
}

package com.isa.jjdzr.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.isa.jjdzr.market.Market;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class WalletAsset {
    private final String id;
    private final BigDecimal purchasePrice;
    private BigDecimal purchasedQuantity;

    @JsonCreator
    public WalletAsset() {
        this.id = "id";
        this.purchasePrice = new BigDecimal("0");
        this.purchasedQuantity = new BigDecimal("0");
    }

    public WalletAsset(Asset asset, String purchasedQuantity) {
        this.id = asset.getId();
        this.purchasePrice = new BigDecimal(String.valueOf(asset.getCurrentPrice()));
        this.purchasedQuantity = new BigDecimal(purchasedQuantity);

    }
    public BigDecimal getCurrentPrice(Market market) {
        List<Asset> assetList = market.availableAssets();
        for (Asset a : assetList) {
            if (a.getId().equals(this.id)) {
                return a.getCurrentPrice();
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public BigDecimal getPurchasedQuantity() {
        return purchasedQuantity;
    }

    public void reduceAsset(String quantity) {
        this.purchasedQuantity = purchasedQuantity.subtract(new BigDecimal(quantity));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletAsset that = (WalletAsset) o;
        return id.equals(that.id) && purchasePrice.equals(that.purchasePrice) && purchasedQuantity.equals(that.purchasedQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchasePrice, purchasedQuantity);
    }
}

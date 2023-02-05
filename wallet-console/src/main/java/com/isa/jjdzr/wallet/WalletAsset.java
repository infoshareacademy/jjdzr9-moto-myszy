package com.isa.jjdzr.wallet;

import com.isa.jjdzr.assets.Asset;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class WalletAsset {

    private final BigDecimal purchasePrice;
    private final BigDecimal purchasedQuantity;
    private final Asset asset;

    public WalletAsset(Asset asset, String purchasedQuantity) {
        this.purchasePrice = new BigDecimal(String.valueOf(asset.getCurrentPrice()));
        this.purchasedQuantity = new BigDecimal(purchasedQuantity);
        this.asset = asset;
    }

    public BigDecimal getCurrentValue() {
        return asset.getCurrentPrice().multiply(purchasedQuantity);
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public BigDecimal getPurchasedQuantity() {
        return purchasedQuantity;
    }

    public Asset getAsset() {
        return asset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletAsset that = (WalletAsset) o;
        return purchasePrice.equals(that.purchasePrice) && purchasedQuantity.equals(that.purchasedQuantity) && asset.equals(that.asset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchasePrice, purchasedQuantity, asset);
    }

}

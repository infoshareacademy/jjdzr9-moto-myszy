package com.isa.jjdzr.wallet;

import com.isa.jjdzr.assets.Asset;

import java.math.BigDecimal;
import java.util.Objects;

public class WalletAsset {
    private final String id;
    private final BigDecimal purchasePrice;
    private final BigDecimal purchasedQuantity;
    private final Asset asset;

    public WalletAsset(Asset asset, double purchasedQuantity) {
        this.id = asset.getId();
        this.purchasePrice = BigDecimal.valueOf(asset.getCurrentPrice().doubleValue());
        this.purchasedQuantity = BigDecimal.valueOf(purchasedQuantity);
        this.asset = asset;
    }

    public BigDecimal getCurrentValue() {
        return asset.getCurrentPrice().multiply(purchasedQuantity);
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

    public Asset getAsset() {
        return asset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletAsset that = (WalletAsset) o;
        return id.equals(that.id) && purchasePrice.equals(that.purchasePrice) && asset.equals(that.asset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchasePrice, asset);
    }

    @Override
    public String toString() {
        return "Nazwa: " + id +"\n"
                + "Cena zakupu: " + purchasePrice + "\n"
                + "Ilość: " + purchasedQuantity + "\n"
                + "Aktualna cena: " + asset.getCurrentPrice() + "\n"
                + "Aktualna wartość: " + getCurrentValue();
    }
}

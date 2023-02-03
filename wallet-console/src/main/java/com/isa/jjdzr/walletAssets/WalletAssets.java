package com.isa.jjdzr.walletAssets;

import com.isa.jjdzr.assets.Assets;

import java.math.BigDecimal;
import java.util.Objects;

public class WalletAssets {
    private String id;
    private BigDecimal purchasePrice;
    private Assets asset;

    public WalletAssets(Assets asset) {
        this.id = asset.getId();
        this.purchasePrice = BigDecimal.valueOf(asset.getCurrentPrice().doubleValue());
        this.asset = asset;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Assets getAsset() {
        return asset;
    }

    public void setAsset(Assets asset) {
        this.asset = asset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletAssets that = (WalletAssets) o;
        return id.equals(that.id) && purchasePrice.equals(that.purchasePrice) && asset.equals(that.asset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchasePrice, asset);
    }
}

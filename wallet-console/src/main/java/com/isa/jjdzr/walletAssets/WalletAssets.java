package com.isa.jjdzr.walletAssets;

import com.isa.jjdzr.assets.Assets;

import java.math.BigDecimal;
import java.util.Objects;

public class WalletAssets {
    private final String id;
    private final BigDecimal purchasePrice;
    private final BigDecimal purchasedQuantity;
    private final Assets asset;

    public WalletAssets(Assets asset, double purchasedQuantity) {
        this.id = asset.getId();
        this.purchasePrice = BigDecimal.valueOf(asset.getCurrentPrice().doubleValue());
        this.purchasedQuantity = BigDecimal.valueOf(purchasedQuantity);
        this.asset = asset;
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

    public Assets getAsset() {
        return asset;
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

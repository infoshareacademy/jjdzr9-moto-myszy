package com.isa.jjdzr.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class WalletAsset {
    private final String id;
    private final BigDecimal purchasePrice;
    private final BigDecimal purchasedQuantity;


    public WalletAsset(Asset asset, String purchasedQuantity) {
        this.id = asset.getId();
        this.purchasePrice = new BigDecimal(String.valueOf(asset.getCurrentPrice()));
        this.purchasedQuantity = new BigDecimal(purchasedQuantity);

    }

    public WalletAsset(String id, BigDecimal purchasePrice, BigDecimal purchasedQuantity) {
        this.id = id;
        this.purchasePrice = purchasePrice;
        this.purchasedQuantity = purchasedQuantity;
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

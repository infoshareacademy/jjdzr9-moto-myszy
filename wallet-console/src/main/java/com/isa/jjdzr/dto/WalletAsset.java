package com.isa.jjdzr.dto;

import java.math.BigDecimal;

public class WalletAsset {
    private final String id;
    private final BigDecimal purchasePrice;
    private final BigDecimal purchasedQuantity;


    public WalletAsset(Asset asset, String purchasedQuantity) {
        this.id = asset.getId();
        this.purchasePrice = new BigDecimal(String.valueOf(asset.getCurrentPrice()));
        this.purchasedQuantity = new BigDecimal(purchasedQuantity);

    }



    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public BigDecimal getPurchasedQuantity() {
        return purchasedQuantity;
    }





}

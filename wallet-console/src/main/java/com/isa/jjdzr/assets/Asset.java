package com.isa.jjdzr.assets;

import java.math.BigDecimal;
import java.util.Objects;

public class Asset {
    private String id;
    private BigDecimal currentPrice;

    public Asset(String id, BigDecimal currentPrice) {
        this.id = id;
        this.currentPrice = currentPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asset asset = (Asset) o;
        return id.equals(asset.id) && currentPrice.equals(asset.currentPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currentPrice);
    }

    @Override
    public String toString() {
        return "Nazwa: " + id +"\n"
                + "Aktualna cena: " + currentPrice;
    }
}

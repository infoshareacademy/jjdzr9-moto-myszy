package com.isa.jjdzr.walletweb.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wallet_asset_id", nullable = false)
    private WalletAsset walletAsset;

    @Column(name = "quantity", nullable = false)
    private BigDecimal quantity;

    @Column(name = "type", nullable = false)
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WalletAsset getWalletAsset() {
        return walletAsset;
    }

    public void setWalletAsset(WalletAsset walletAsset) {
        this.walletAsset = walletAsset;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


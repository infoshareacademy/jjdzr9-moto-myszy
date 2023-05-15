package com.isa.jjdzr.walletweb.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "WalletAsset")
public class WalletAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Column(name = "purchase_price", nullable = false)
    private BigDecimal purchasePrice;

    // ... the rest of the fields

    // getters and setters
}

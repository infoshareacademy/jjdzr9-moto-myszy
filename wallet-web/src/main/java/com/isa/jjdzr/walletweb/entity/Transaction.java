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

    // getters and setters
}


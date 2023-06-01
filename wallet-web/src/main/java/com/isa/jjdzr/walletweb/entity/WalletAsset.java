package com.isa.jjdzr.walletweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class WalletAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "asset_id", nullable = false)
    private String assetId;

    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @Column(name = "purchase_price", nullable = false)
    private BigDecimal purchasePrice;

    @Column(name = "current_price", nullable = false)
    private BigDecimal currentPrice; //TODO: do I need this ?

    @Column(name = "quantity", nullable = false)
    private BigDecimal quantity;
}

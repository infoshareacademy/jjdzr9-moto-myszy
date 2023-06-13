package com.isa.jjdzr.walletweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wallet_name", nullable = false)
    private String walletName;

    @Column(name = "cash", nullable = false)
    private BigDecimal cash;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany
    private List<WalletAssetEntity> walletAssetEntity;
}

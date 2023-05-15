package com.isa.jjdzr.walletweb.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "cash", nullable = false)
    private BigDecimal cash;

    // getters and setters
}

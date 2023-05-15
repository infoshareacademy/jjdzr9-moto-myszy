package com.isa.jjdzr.walletweb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Asset")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "asset_id", nullable = false)
    private String assetId;

    @Column(name = "asset_name", nullable = false)
    private String assetName;

    // getters and setters
}

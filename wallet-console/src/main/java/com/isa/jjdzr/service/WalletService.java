package com.isa.jjdzr.service;

import com.isa.jjdzr.repositories.WalletRepository;

public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletAssetService walletAssetService;


    public WalletService() {
        this.walletRepository = new WalletRepository();
        this.walletAssetService = new WalletAssetService();
    }
}

package com.isa.jjdzr.service;

import com.isa.jjdzr.dto.Wallet;
import com.isa.jjdzr.repositories.WalletRepository;

import java.math.BigDecimal;

public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletAssetService walletAssetService;


    public WalletService() {
        this.walletRepository = new WalletRepository();
        this.walletAssetService = new WalletAssetService();
    }

    public Long generateWallet(String cash) {
        Wallet wallet = new Wallet();
        BigDecimal cashToAdd = new BigDecimal(cash);
        wallet.setCash(cashToAdd);
        walletRepository.save(wallet);
        return wallet.getId();
    }
}

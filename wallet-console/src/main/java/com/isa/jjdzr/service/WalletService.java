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

    public Long generateWallet(String walletName, String cash) {
        Wallet wallet = new Wallet();
        wallet.setWalletName(walletName);
        wallet.setId(0L);
        wallet.setCash(new BigDecimal(cash));
        Wallet result = saveWallet(wallet);
        return result.getId();
    }

    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }
}

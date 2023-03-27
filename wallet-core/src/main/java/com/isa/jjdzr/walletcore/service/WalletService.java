package com.isa.jjdzr.walletcore.service;

import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletcore.dto.WalletAsset;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {
    Long generateWallet(String walletName, String cash);
    Wallet saveWallet(Wallet wallet);
    List<Wallet> getUserWallets(Long userId);
    Wallet find(Long walletId);
    void spendCash(Long walletId, WalletAsset walletAsset);
    List<WalletAsset> findWalletAssets(Long walletId);
    void addCashFromTransaction(Long walletId, BigDecimal quantityB, BigDecimal currentPrice);
    void topUpWallet(Long walletId, BigDecimal cash);
}

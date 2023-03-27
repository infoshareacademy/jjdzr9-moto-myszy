package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletcore.service.WalletAssetService;
import com.isa.jjdzr.walletcore.service.WalletService;
import com.isa.jjdzr.walletweb.dto.TopUpDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class WalletWebService {
    private final WalletService walletServiceImpl;
    private final WalletAssetService walletAssetServiceImpl;

    public Wallet find(Long walletId) {
        return walletServiceImpl.find(walletId);
    }

    public List<WalletAsset> findWalletAssetsByWalletId(Long walletId) {
        return walletAssetServiceImpl.findWalletAssetsByWalletId(walletId);
    }

    public WalletAsset findCurrentPrice(Long id) {
        return walletAssetServiceImpl.findCurrentPrice(id);
    }

    public List<Wallet> getUserWallets(Long userId) {
        return walletServiceImpl.getUserWallets(userId);
    }

    public Wallet saveWallet(Wallet wallet) {
        return walletServiceImpl.saveWallet(wallet);
    }

    public void topUpWallet(Long walletId, TopUpDto topUpDto) {
        walletServiceImpl.topUpWallet(walletId, topUpDto.getCash());
    }
}

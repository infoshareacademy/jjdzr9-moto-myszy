package com.isa.jjdzr.walletcore.service;

import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletcore.repositories.WalletRepository;

import java.math.BigDecimal;
import java.util.List;

public class WalletServiceImpl implements WalletService{
    private final WalletRepository walletRepository;
    private final WalletAssetServiceImpl walletAssetServiceImpl;


    public WalletServiceImpl() {
        this.walletRepository = new WalletRepository();
        this.walletAssetServiceImpl = new WalletAssetServiceImpl();
    }

    @Override
    public Long generateWallet(String walletName, String cash) {
        Wallet wallet = new Wallet();
        wallet.setWalletName(walletName);
        wallet.setUserId(0L);
        wallet.setCash(new BigDecimal(cash));
        Wallet result = saveWallet(wallet);
        return result.getId();
    }
    @Override
    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }


    @Override
    public List<Wallet> getUserWallets(Long userId) {
        return walletRepository.getAll()
                .stream()
                .filter(w -> w.getUserId().equals(userId))
                .toList();
    }

    @Override
    public Wallet find(Long walletId) {
        return walletRepository.find(walletId);
    }

    @Override
    public void spendCash(Long walletId, WalletAsset walletAsset) {
        Wallet wallet = walletRepository.find(walletId);
        BigDecimal cashToSpend = walletAsset.getPurchasePrice().multiply(walletAsset.getQuantity());
        reduceCash(wallet, cashToSpend);
    }

    @Override
    public List<WalletAsset> findWalletAssets(Long walletId) {
        return walletAssetServiceImpl.findWalletAssetsByWalletId(walletId);
    }
    @Override
    public void addCashFromTransaction(Long walletId, BigDecimal quantityB, BigDecimal currentPrice) {
        Wallet wallet = walletRepository.find(walletId);
        BigDecimal cashToAdd = quantityB.multiply(currentPrice);
        BigDecimal newCashAmount = wallet.getCash().add(cashToAdd);
        wallet.setCash(newCashAmount);
        walletRepository.save(wallet);
    }
    @Override
    public void topUpWallet(Long walletId, BigDecimal cash) {
        Wallet wallet = find(walletId);
        BigDecimal newCashAmount = wallet.getCash().add(cash);
        wallet.setCash(newCashAmount);
        saveWallet(wallet);
    }

    private void reduceCash(Wallet wallet, BigDecimal cashToSpend) {
        BigDecimal newCashAmount = wallet.getCash().subtract(cashToSpend);
        wallet.setCash(newCashAmount);
        walletRepository.save(wallet);
    }
}

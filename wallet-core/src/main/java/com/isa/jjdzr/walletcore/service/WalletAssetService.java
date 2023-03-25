package com.isa.jjdzr.walletcore.service;

import com.isa.jjdzr.walletcore.dto.WalletAsset;

import java.math.BigDecimal;
import java.util.List;

public interface WalletAssetService {
    WalletAsset save(WalletAsset walletAsset);
    List<WalletAsset> findWalletAssetsByWalletId(Long walletId);
    WalletAsset findCurrentPrice(Long waIndex);
    WalletAsset find(Long walletAssetId);
    void sellWalletAsset(Long walletAssetId, BigDecimal quantity);
}

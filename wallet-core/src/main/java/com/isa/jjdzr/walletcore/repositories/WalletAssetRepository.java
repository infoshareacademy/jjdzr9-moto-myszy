package com.isa.jjdzr.walletcore.repositories;

import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletcore.dto.WalletAsset;

import java.util.List;

public interface WalletAssetRepository {
    WalletAsset save(WalletAsset walletAsset);
    WalletAsset find(Long id);
    List<WalletAsset> getAll();
    void delete(Long id);
}

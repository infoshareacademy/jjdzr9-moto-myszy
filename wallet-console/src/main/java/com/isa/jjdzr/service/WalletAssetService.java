package com.isa.jjdzr.service;

import com.isa.jjdzr.dto.Wallet;
import com.isa.jjdzr.dto.WalletAsset;
import com.isa.jjdzr.repositories.WalletAssetRepository;

public class WalletAssetService {

    private final WalletAssetRepository walletAssetRepository;

    public WalletAssetService() {
        this.walletAssetRepository = new WalletAssetRepository();
    }

    public WalletAsset save(WalletAsset walletAsset) {
        return walletAssetRepository.save(walletAsset);
    }
}

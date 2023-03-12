package com.isa.jjdzr.service;

import com.isa.jjdzr.repositories.WalletAssetRepository;

public class WalletAssetService {

    private final WalletAssetRepository walletAssetRepository;

    public WalletAssetService() {
        this.walletAssetRepository = new WalletAssetRepository();
    }
}

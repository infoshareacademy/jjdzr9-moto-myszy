package com.isa.jjdzr.brokerlogic;

import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletcore.service.WalletAssetServiceImpl;
import com.isa.jjdzr.walletcore.service.WalletServiceImpl;

import java.math.BigDecimal;

public class BrokerLogicBuy {
    private final WalletAssetServiceImpl walletAssetServiceImpl;
    private final WalletServiceImpl walletServiceImpl;

    public BrokerLogicBuy() {
        this.walletAssetServiceImpl = new WalletAssetServiceImpl();
        this.walletServiceImpl = new WalletServiceImpl();
    }

    public Long buy(Asset asset, Long walletId, String quantity) {
        WalletAsset walletAsset = createWalletAsset(walletId, asset, quantity);
        walletServiceImpl.spendCash(walletId, walletAsset);
        return walletId;
    }

    private WalletAsset createWalletAsset(Long walletId, Asset asset, String quantity) {
        WalletAsset walletAsset = new WalletAsset();
        walletAsset.setQuantity(new BigDecimal(quantity));
        walletAsset.setWalletId(walletId);
        walletAsset.setAssetId(asset.getId());
        walletAsset.setPurchasePrice(asset.getCurrentPrice());
        return walletAssetServiceImpl.save(walletAsset);
    }


}

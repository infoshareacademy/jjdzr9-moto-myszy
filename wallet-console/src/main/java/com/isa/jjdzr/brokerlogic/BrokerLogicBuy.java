package com.isa.jjdzr.brokerlogic;

import com.isa.jjdzr.dto.Asset;
import com.isa.jjdzr.dto.WalletAsset;
import com.isa.jjdzr.service.WalletAssetService;
import com.isa.jjdzr.service.WalletService;

import java.math.BigDecimal;

public class BrokerLogicBuy {
    private final WalletAssetService walletAssetService;
    private final WalletService walletService;

    public BrokerLogicBuy(){
        this.walletAssetService = new WalletAssetService();
        this.walletService = new WalletService();
    }

    public Long buy(Asset asset, Long walletId, String quantity){
        WalletAsset walletAsset = createWalletAsset(walletId, asset, quantity);
        walletService.spendCash(walletId, walletAsset);
        return walletId;
    }

    private WalletAsset createWalletAsset(Long walletId, Asset asset, String quantity) {
        WalletAsset walletAsset = new WalletAsset();
        walletAsset.setQuantity(new BigDecimal(quantity));
        walletAsset.setWalletId(walletId);
        walletAsset.setAssetId(asset.getId());
        walletAsset.setPurchasePrice(asset.getCurrentPrice());
        return walletAssetService.save(walletAsset);
    }


}

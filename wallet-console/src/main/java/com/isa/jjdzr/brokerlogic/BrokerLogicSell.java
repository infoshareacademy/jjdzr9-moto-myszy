package com.isa.jjdzr.brokerlogic;

import com.isa.jjdzr.dto.WalletAsset;
import com.isa.jjdzr.service.WalletAssetService;
import com.isa.jjdzr.service.WalletService;

import java.math.BigDecimal;

public class BrokerLogicSell {
    private final WalletAssetService walletAssetService;
    private final WalletService walletService;

    public BrokerLogicSell() {
        this.walletAssetService = new WalletAssetService();
        this.walletService = new WalletService();
    }

    public void sell(Long walletId, Long walletAssetId, String quantity) {
        BigDecimal quantityB = new BigDecimal(quantity);
        WalletAsset walletAsset = walletAssetService.find(walletAssetId);
        walletAssetService.sellWalletAsset(walletAssetId, quantityB);
        walletService.addCashFromTransaction(walletId, quantityB, walletAsset.getCurrentPrice());
    }

}

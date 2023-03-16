package com.isa.jjdzr.brokerlogic;

import com.isa.jjdzr.dto.Wallet;
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

    public void sell(Long walletId, Long walletAssetId, String quantity, BigDecimal currentPrice) {
        BigDecimal quantityB = new BigDecimal(quantity);
        walletAssetService.changeWalletAsset(walletAssetId, quantityB);
        walletService.addCashFromTransaction(walletId, quantityB, currentPrice);
    }

}

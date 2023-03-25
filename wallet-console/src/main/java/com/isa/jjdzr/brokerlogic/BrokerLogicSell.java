package com.isa.jjdzr.brokerlogic;

import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletcore.service.WalletAssetServiceImpl;
import com.isa.jjdzr.walletcore.service.WalletServiceImpl;

import java.math.BigDecimal;

public class BrokerLogicSell {
    private final WalletAssetServiceImpl walletAssetServiceImpl;
    private final WalletServiceImpl walletServiceImpl;

    public BrokerLogicSell() {
        this.walletAssetServiceImpl = new WalletAssetServiceImpl();
        this.walletServiceImpl = new WalletServiceImpl();
    }

    public void sell(Long walletId, Long walletAssetId, String quantity) {
        BigDecimal quantityB = new BigDecimal(quantity);
        WalletAsset walletAsset = walletAssetServiceImpl.find(walletAssetId);
        walletAssetServiceImpl.sellWalletAsset(walletAssetId, quantityB);
        walletServiceImpl.addCashFromTransaction(walletId, quantityB, walletAsset.getCurrentPrice());
    }

}

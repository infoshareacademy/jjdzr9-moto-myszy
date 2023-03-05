package com.isa.jjdzr.brokerlogic;

import com.isa.jjdzr.dto.Asset;
import com.isa.jjdzr.dto.WalletAsset;
import com.isa.jjdzr.dto.Wallet;

import java.math.BigDecimal;

public class BrokerLogicBuy {

    public void buy(Asset asset, Wallet wallet, String quantity){
        createWalletAsset(wallet, asset, quantity);
        spendCash(wallet, asset, quantity);
    }
    //TODO: put this in wallet service
    private void createWalletAsset(Wallet wallet, Asset asset, String quantity) {
        WalletAsset wa = new WalletAsset(asset, quantity);
        wallet.addAsset(wa);
    }
    //TODO: put this in wallet service
    private void spendCash(Wallet wallet, Asset asset, String quantity) {
        BigDecimal cashToSpend = new BigDecimal(quantity).multiply(asset.getCurrentPrice());
        wallet.spendCash(cashToSpend);
    }

}

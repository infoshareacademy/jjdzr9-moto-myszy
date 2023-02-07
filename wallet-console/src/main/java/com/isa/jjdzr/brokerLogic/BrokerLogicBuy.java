package com.isa.jjdzr.brokerLogic;

import com.isa.jjdzr.dto.Asset;
import com.isa.jjdzr.market.Market;
import com.isa.jjdzr.dto.Wallet;

public class BrokerLogicBuy {

    private Asset getAssetToBuy(Market market, int index) {

        return new Asset("a", "0");
    }
    public void createWalletAsset(Wallet wallet, Asset asset, String quantity) {
        //tworzy nowy obiekt i dodaje go to Wallet
    }

    private void spendCash(Wallet Wallet, Asset asset, String quantity) {
        //wydaje pieniadze na zakup
    }

}

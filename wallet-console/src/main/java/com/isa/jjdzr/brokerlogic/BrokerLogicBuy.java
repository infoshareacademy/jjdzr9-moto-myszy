package com.isa.jjdzr.brokerlogic;

import com.isa.jjdzr.dto.Asset;
import com.isa.jjdzr.dto.WalletAsset;
import com.isa.jjdzr.market.Market;
import com.isa.jjdzr.dto.Wallet;

public class BrokerLogicBuy {

    public void buy(Market market, Wallet wallet, int index, String quantity){
        Asset toBuy = getAssetToBuy(market, index);
        createWalletAsset(wallet, toBuy, quantity);
        spendCash(wallet, toBuy, quantity);
    }

    private Asset getAssetToBuy(Market market, int index) {

        return new Asset("a", "0");
    }
    private void createWalletAsset(Wallet wallet, Asset asset, String quantity) {
        //tworzy nowy obiekt i dodaje go to Wallet
    }

    private void spendCash(Wallet Wallet, Asset asset, String quantity) {
        //wydaje pieniadze na zakup
    }

}

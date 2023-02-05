package com.isa.jjdzr.brokerLogic;

import com.isa.jjdzr.assets.Asset;
import com.isa.jjdzr.assets.AssetsList;
import com.isa.jjdzr.wallet.Wallet;

import java.util.ArrayList;

public class BrokerLogicBuy {

    private Asset getAssetToBuy(AssetsList assetsList, int index) {
        //ma pobierać od usera jaki aktyw chcemy kupić
        return new Asset("a", "0");
    }
    public void createWalletAsset(Wallet wallet, Asset asset, String quantity) {
        //tworzy nowy obiekt i dodaje go to Wallet
    }

    private void spendCash(Wallet Wallet, Asset asset, String quantity) {
        //wydaje pieniadze na zakup
    }

}

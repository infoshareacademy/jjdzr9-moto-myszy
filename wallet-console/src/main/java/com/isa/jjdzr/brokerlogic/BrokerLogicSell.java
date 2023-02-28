package com.isa.jjdzr.brokerlogic;

import com.isa.jjdzr.dto.Wallet;
import com.isa.jjdzr.market.Market;

import java.math.BigDecimal;

public class BrokerLogicSell {

    public void sell(Wallet wallet, int index, String quantity, BigDecimal currentPrice) {
        changeWalletAsset(wallet, index, quantity);
        addCash(wallet, quantity, currentPrice);
    }
    //TODO: put this in wallet service
    private void changeWalletAsset(Wallet wallet, int index, String quantity) {
        //na podstawie tych danych modyfikuje nam obiekt WalletAsset, który chcemy sprzedać lub będzie go usuwać z listy jeśli sprzedamy całosć
        if (wallet.getWallet().get(index).getPurchasedQuantity().compareTo(new BigDecimal(quantity)) == 0) {
            wallet.getWallet().remove(index);
        } else {
            wallet.getWallet().get(index).reduceAsset(quantity);
        }
    }
    //TODO: put this in wallet service
    private void addCash(Wallet wallet,String quantity, BigDecimal currentPrice) {
        wallet.addCash(new BigDecimal(quantity).multiply(currentPrice));
    }
}

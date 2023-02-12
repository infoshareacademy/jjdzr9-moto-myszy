package com.isa.jjdzr.brokerlogic;

import com.isa.jjdzr.dto.Wallet;
import com.isa.jjdzr.market.Market;

public class BrokerLogicSell {

    public void sell(Wallet wallet, int index, String quantity, Market market) {
        changeWalletAsset(wallet, index, quantity, market);
        addCash(wallet, index, quantity, market);
    }

    private void changeWalletAsset(Wallet wallet, int index, String quantity, Market market) {
        //na podstawie tych danych modyfikuje nam obiekt WalletAsset, który chcemy sprzedać lub będzie go usuwać z listy jeśli sprzedamy całosć
    }
    private void addCash(Wallet wallet, int index, String quantity, Market market) {
        //dodaje gotówkę (ilość sprzedana * aktualna cena)
    }
}

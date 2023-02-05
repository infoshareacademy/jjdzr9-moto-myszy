package com.isa.jjdzr.brokerLogic;

import com.isa.jjdzr.wallet.Wallet;

public class BrokerLogicSell {

    public void changeWalletAsset(Wallet wallet, int index, String quantity) {
        //na podstawie tych danych modyfikuje nam obiekt WalletAsset, który chcemy sprzedać lub będzie go usuwać z listy jeśli sprzedamy całosć
    }
    private void addCash(Wallet wallet, int index, String quantity) {
        //dodaje gotówkę (ilość sprzedana * aktualna cena)
    }
}

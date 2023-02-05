package com.isa.jjdzr.buyAndSell;

import com.isa.jjdzr.wallet.WalletAsset;

import java.math.BigDecimal;

public class BrokerLogicSell {

    private WalletAsset asset;
    private BigDecimal quantityToSell;

    private void changeWalletAsset() {
        //na podstawie tych danych modyfikuje nam obiekt WalletAsset, który chcemy sprzedać lub będzie go usuwać z listy jeśli sprzedamy całosć
    }
    private void addCash() {
        //dodaje gotówkę (ilość sprzedana * aktualna cena)
    }
}

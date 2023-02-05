package com.isa.jjdzr.menu;

import com.isa.jjdzr.assets.Asset;
import com.isa.jjdzr.assets.AssetsList;
import com.isa.jjdzr.wallet.Wallet;

import java.util.ArrayList;

public class Broker {
    public void buy() {
        // ma pobierać jaki aktyw chcemy kupić
        // ma pobierać ile chcemy go kupić
        // sprawdzic czy wystarczy nam kasy na zakup
        // na podstawie tych danych tworzy nowy obiekt WalletAsset
        // dodaje ten obiekt do Wallet

    }

    private int getAssetIndex(AssetsList assetsList) {
        //ma pobierać od usera jaki aktyw chcemy kupić
        //walidacja !
        return 0;
    }

    private String getQuantityToBuy(Wallet wallet) {
        // ma pobierać ile chcemy go kupić
        // sprawdza czy wprowadzone dane mają tylko 0-9 i .
        // sprawdzic czy wystarczy nam kasy na zakup
        return "";
    }

    private boolean checkCash(Wallet wallet){
        //sprawdza czy wystarczy nam pieniedzy na zakup
        return true;
    }

    public void sell() {
        //ma pobierać jaki aktyw chcemy sprzedać
        //ma pobierać ile chcemy go sprzedać
        //sprawdzić czy mamy taką ilość w Wallet
        //na podstawie tych danych modyfikuje nam obiekt WalletAsset wyciągnięty z listy
        //dodaje gotówkę (ilość sprzedana * aktualna cena)
    }

    private int getWalletAssetIndex(Wallet wallet) {
        //ma pobierać od usera jaki aktyw chcemy sprzedać
        return 0;
    }

    private String getQuantityToSell(Wallet wallet, int index) {
        // ma pobierać ile chcemy go sprzedać
        // sprawdza czy wprowadzone dane mają tylko 0-9 i .
        //sprawdzić czy mamy taką ilość w Wallet
        return "";
    }

    private boolean checkQuantity(Wallet wallet, int index, String quantity) {
        //sprawdzić czy mamy taką ilość w Wallet
        return false;
    }

    private boolean checkStringInput(String str) {
        // sprawdza czy wprowadzone dane mają tylko 0-9 i .
        return false;
    }
}

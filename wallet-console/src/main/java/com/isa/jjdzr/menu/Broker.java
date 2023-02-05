package com.isa.jjdzr.menu;

import java.util.ArrayList;

public class Broker {
    public void buy() {
        // ma pobierać jaki aktyw chcemy kupić
        // ma pobierać ile chcemy go kupić
        // sprawdzic czy wystarczy nam kasy na zakup
        // na podstawie tych danych tworzy nowy obiekt WalletAsset
        // dodaje ten obiekt do Wallet

    }

    private int getAssetToBuy() {
        //ma pobierać od usera jaki aktyw chcemy kupić
        return 0;
    }

    private String getQuantityToBuy() {
        // ma pobierać ile chcemy go kupić
        // sprawdza czy wprowadzone dane mają tylko 0-9 i .
        // sprawdzic czy wystarczy nam kasy na zakup
        return "";
    }

    private boolean checkCash(){
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

    private int getAssetToSell() {
        //ma pobierać od usera jaki aktyw chcemy sprzedać
        return 0;
    }

    private String getQuantityToSell() {
        // ma pobierać ile chcemy go sprzedać
        // sprawdza czy wprowadzone dane mają tylko 0-9 i .
        //sprawdzić czy mamy taką ilość w Wallet
        return "";
    }

    private boolean checkQuantity() {
        //sprawdzić czy mamy taką ilość w Wallet
        return false;
    }

    private boolean checkStringInput(String str) {
        // sprawdza czy wprowadzone dane mają tylko 0-9 i .
        return false;
    }

    private void showAssets(ArrayList assetList){
        //pokazuje assety na podstawie zadaniej listy
        //potrzebna metoda drukująca aktywa

    }

}

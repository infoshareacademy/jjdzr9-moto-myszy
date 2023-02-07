package com.isa.jjdzr.consolePresentation;

import com.isa.jjdzr.dto.Wallet;

import java.util.Scanner;
import java.util.regex.Pattern;

class WalletGenerator {
    public Wallet generateWallet() {

        System.out.println("Utworzymy teraz nowy portfel inwestycyjny.");
        Wallet wallet = new Wallet();
        wallet.addCash(getCashAmount());
        return wallet;
    }

    private String getCashAmount() {
        Scanner scan = new Scanner(System.in);
        String cash;
        System.out.println("Podaj jaką kwotą chcesz zasilić portfel: ");
        cash = scan.nextLine();
        while (isInvalid(cash)) {
            System.out.println("Nieprawidłowa wartość. Podaj kwotę: ");
            cash = scan.nextLine();
        }

        return cash;
    }

    private boolean isInvalid(String str) {
        String validSymbols = "[0-9.]*";
        return !Pattern.matches(validSymbols, str);
    }
}

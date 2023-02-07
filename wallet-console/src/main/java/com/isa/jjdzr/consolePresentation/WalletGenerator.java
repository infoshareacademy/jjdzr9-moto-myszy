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
        cash = replaceComma(cash);
        while (isInvalid(cash)) {
            System.err.println("Nieprawidłowa wartość. Podaj kwotę: ");
            cash = scan.nextLine();
            cash = replaceComma(cash);
        }
        System.out.println("Portfel zasilony kwotą: " + cash);
        System.out.println("Powrót do menu głównego.");

        return cash;
    }

    private boolean isInvalid(String str) {
        int countDots = (int) str.chars().filter(ch -> ch == '.').count();
        if (countDots > 1) {
            return true;
        } else if (str.length() > 20) {
            System.err.println("Zbyt duża kwota!");
            return true;
        } else if (str.charAt(0) == ('0')) {
            System.err.println("Kwota nie może zaczynać się od 0!");
            return true;
        } else {
            String validSymbols = "[0-9.]*";
            return !Pattern.matches(validSymbols, str);
        }
    }

    private String replaceComma(String str){
        return str.replace(',','.');
    }
}

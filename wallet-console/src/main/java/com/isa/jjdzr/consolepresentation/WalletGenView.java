package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.dto.Wallet;
import com.isa.jjdzr.walletgenerator.WalletGenerator;

import java.util.Scanner;
import java.util.regex.Pattern;

class WalletGenView {
    public Wallet start(Wallet wallet) {
        System.out.println("Witam w generatorze portfela inwestycyjnego.");
        if (doYouWantToContinue()) {
            printDontCreateMessage();
        } else {
            if (wallet != null) {
                System.out.println("Portfel nie jest pusty. Utworzenie nowego spowoduje utratę niezapisanych zmian.");
                if (doYouWantToContinue()) {
                    printDontCreateMessage();
                    return wallet;
                }
            }
            System.out.println("Utworzymy teraz nowy portfel inwestycyjny.");
            String cash = getCashAmount();
            wallet = new WalletGenerator().generateWallet(cash);
        }
        return wallet;
    }

    public void addCash(Wallet wallet) {
        String cash = getCashAmount();
        wallet.addCash(cash);
    }

    private boolean doYouWantToContinue() { //sprawdza czy chcesz kontynuowac i jesli tak to wywala false
        Scanner scan = new Scanner(System.in);
        System.out.println("Czy chcesz kontynuować [T/N]?");
        String decision = scan.nextLine();
        while (isInvalid(decision)) {
            System.err.println("Zła wartość, podaj T lub N:");
            decision = scan.nextLine();
        }
        return !decision.equalsIgnoreCase("T");
    }

    private boolean isInvalid(String str) {
        String validSymbols = "[TN]?";
        return !Pattern.matches(validSymbols, str.toUpperCase());
    }

    private void printDontCreateMessage() {
        System.out.println("Portfel nie zostanie utworzony.");
        System.out.println("Powrót do menu głównego");
    }
    private String getCashAmount() {
        Scanner scan = new Scanner(System.in);
        String cash;
        System.out.println("Podaj jaką kwotą chcesz zasilić portfel: ");
        cash = scan.nextLine();
        cash = replaceComma(cash);
        while (isInvalidCash(cash)) {
            System.err.println("Nieprawidłowa wartość. Podaj kwotę: ");
            cash = scan.nextLine();
            cash = replaceComma(cash);
        }
        System.out.println("Portfel zasilony kwotą: " + cash);
        System.out.println("Powrót do menu głównego.");

        return cash;
    }

    private boolean isInvalidCash(String str) {
        int countDots = (int) str.chars().filter(ch -> ch == '.').count();
        if (countDots > 1) {
            return true;
        } else if (str.length() > 10) {
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



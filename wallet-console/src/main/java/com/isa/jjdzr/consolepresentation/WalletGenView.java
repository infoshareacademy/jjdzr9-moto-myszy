package com.isa.jjdzr.consolePresentation;

import com.isa.jjdzr.dto.Wallet;

import java.util.Scanner;
import java.util.regex.Pattern;

public class WalletGenView {
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
            wallet = new WalletGenerator().generateWallet();
        }
        return wallet;
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


}

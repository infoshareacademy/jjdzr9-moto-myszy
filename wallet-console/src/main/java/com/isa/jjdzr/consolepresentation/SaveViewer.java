package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.datareadandwrite.WalletSaver;
import com.isa.jjdzr.dto.Wallet;

import java.util.Scanner;
import java.util.regex.Pattern;

public class SaveViewer {
    public void save(Wallet wallet) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Za chwilę nastąpi zapis portfela do pliku.");
        if (doYouWantToContinue()) {
            new WalletSaver().saveWallet(wallet);
            System.out.println("Zapis zakończony sukcesem.");
        }
    }

    private boolean doYouWantToContinue() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Czy chcesz kontynuować [T/N]?");
        String decision = scan.nextLine();
        while (isInvalid(decision)) {
            System.err.println("Zła wartość, podaj T lub N:");
            decision = scan.nextLine();
        }
        return decision.equalsIgnoreCase("T");
    }

    private boolean isInvalid(String str) {
        String validSymbols = "[TN]?";
        return !Pattern.matches(validSymbols, str.toUpperCase());
    }
}

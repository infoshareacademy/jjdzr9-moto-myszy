package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.console.Printable;
import com.isa.jjdzr.console.Printer;
import com.isa.jjdzr.datareadandwrite.WalletLoader;
import com.isa.jjdzr.dto.Wallet;

import java.util.Scanner;
import java.util.regex.Pattern;

public class LoadViewer {
    private Printable printer = new Printer();
    public Wallet load() {
        Scanner scan = new Scanner(System.in);
        Wallet wallet = null;
        printer.printActualLine("Za chwilę nastąpi wczytanie portfela.");
        if (doYouWantToContinue()) {
            wallet = new WalletLoader().loadWallet();
            if (wallet == null) {
                System.out.println("Nie udało się wczytać portfela.");
            } else {
                System.out.println("Wczytanie zakończone sukcesem.");
            }
        }
        return wallet;
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

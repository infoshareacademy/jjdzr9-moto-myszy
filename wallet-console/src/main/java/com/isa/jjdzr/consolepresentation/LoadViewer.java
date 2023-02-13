package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.datareadandwrite.WalletLoader;
import com.isa.jjdzr.dto.Wallet;

import java.util.Scanner;
import java.util.regex.Pattern;

class LoadViewer {
    public Wallet load() {
        Scanner scan = new Scanner(System.in);
        Wallet wallet = null;
        System.out.println("Za chwilę nastąpi wczytanie portfela.");
        if (doYouWantToContinue()) {
            wallet = new WalletLoader().loadWallet();
            System.out.println("Wczytanie zakończone sukcesem.");
        }
        System.out.println("Aby powrócić do menu głównego naciśnij ENTER: ");
        scan.nextLine();
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

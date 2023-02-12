package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.datareadandwrite.WalletSaver;
import com.isa.jjdzr.dto.Wallet;

import java.util.Scanner;
import java.util.regex.Pattern;

class SaveViewer {
    public void save(Wallet wallet) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Czy chcesz zapisać swój portfel [T/N] ?");
        if (doYouWantToContinue()) {
            new WalletSaver().saveWallet(wallet);
        }
        System.out.println("Aby powrócić do menu głównego naciśnij ENTER: ");
        scan.next();
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
}

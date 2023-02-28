package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.console.Printable;
import com.isa.jjdzr.console.Printer;
import com.isa.jjdzr.datareadandwrite.WalletSaver;
import com.isa.jjdzr.dto.Wallet;

import java.util.Scanner;
import java.util.regex.Pattern;

public class SaveViewer {
    private Printable printer = new Printer();
    //TODO: remake to autosave
    public void save(Wallet wallet) {
        printer.printActualLine("Za chwilę nastąpi zapis portfela do pliku.");
        if (doYouWantToContinue()) {
            new WalletSaver().saveWallet(wallet);
            printer.printActualLine("Zapis zakończony sukcesem.");
        }
    }
    //TODO: make this in outside class
    private boolean doYouWantToContinue() {
        Scanner scan = new Scanner(System.in);
        printer.printActualLine("Czy chcesz kontynuować [T/N]?");
        String decision = scan.nextLine();
        while (isInvalid(decision)) {
            printer.printError("Zła wartość, podaj T lub N:");
            decision = scan.nextLine();
        }
        return decision.equalsIgnoreCase("T");
    }
    //TODO: make this in outside class like validator
    private boolean isInvalid(String str) {
        String validSymbols = "[TN]?";
        return !Pattern.matches(validSymbols, str.toUpperCase());
    }
}

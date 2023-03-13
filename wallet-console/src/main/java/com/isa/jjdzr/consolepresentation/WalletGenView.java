package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.console.Printable;
import com.isa.jjdzr.console.Printer;
import com.isa.jjdzr.dto.Wallet;
import com.isa.jjdzr.service.WalletService;
import com.isa.jjdzr.walletgenerator.WalletGenerator;

import java.util.Scanner;
import java.util.regex.Pattern;

public class WalletGenView {
    private final Printable printer;
    private final WalletService walletService;
    public WalletGenView(){
        this.printer = new Printer();
        this.walletService = new WalletService();
    }
    public Long start(Long walletId) {
        printer.printActualLine("Witam w generatorze portfela inwestycyjnego.");
        if (doYouWantToContinue()) {
            printer.printDontCreateMessage();
        } else {
            if (walletId != null) {
                //TODO: change this line/warning
                printer.printActualLine("Portfel nie jest pusty. Utworzenie nowego spowoduje utratę niezapisanych zmian.");
                if (doYouWantToContinue()) {
                    printer.printDontCreateMessage();
                    return walletId;
                }
            }
            printer.printActualLine("Utworzymy teraz nowy portfel inwestycyjny.");
            String cash = getCashAmount();
            walletId = walletService.generateWallet(cash);
        }
        return walletId;
    }
    //TODO: put this in other class (wallet generator/updater)
    public void addCash(Wallet wallet) {
        String cash = getCashAmount();
        wallet.addCash(cash);
    }
    //TODO: put this in other class
    private boolean doYouWantToContinue() { //sprawdza czy chcesz kontynuowac i jesli tak to wywala false
        Scanner scan = new Scanner(System.in);
        printer.printActualLine("Czy chcesz kontynuować [T/N]?");
        String decision = scan.nextLine();
        while (isInvalid(decision)) {
            printer.printError("Zła wartość, podaj T lub N:");
            decision = scan.nextLine();
        }
        return !decision.equalsIgnoreCase("T");
    }
    //TODO: put this in other class like validator and rename
    private boolean isInvalid(String str) {
        String validSymbols = "[TN]?";
        return !Pattern.matches(validSymbols, str.toUpperCase());
    }


    private String getCashAmount() {
        Scanner scan = new Scanner(System.in);
        String cash;
        printer.printActualLine("Podaj jaką kwotą chcesz zasilić portfel: ");
        cash = scan.nextLine();
        cash = replaceComma(cash);
        while (isInvalidCash(cash)) {
            printer.printError("Nieprawidłowa wartość. Podaj kwotę: ");
            cash = scan.nextLine();
            cash = replaceComma(cash);
        }
        printer.printIncomingCash(cash);
        printer.printActualLine("Powrót do menu głównego.");

        return cash;
    }
//    TODO: put validation in other class
//          FIXME: remake this validation with regex
    private boolean isInvalidCash(String str) {
        int countDots = (int) str.chars().filter(ch -> ch == '.').count();
        if (countDots > 1) {
            return true;
        } else if (str.length() > 10) {
            printer.printError("Zbyt duża kwota!");
            return true;
        } else if (str.charAt(0) == ('0')) {
            printer.printError("Kwota nie może zaczynać się od 0!");
            return true;
        } else {
            String validSymbols = "[0-9.]*";
            return !Pattern.matches(validSymbols, str);
        }
    }
// TODO: put in other class
    private String replaceComma(String str){
        return str.replace(',','.');
    }
}



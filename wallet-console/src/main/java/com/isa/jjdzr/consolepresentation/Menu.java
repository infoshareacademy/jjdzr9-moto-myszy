package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.api.ApiNbp;
import com.isa.jjdzr.console.MenuService;
import com.isa.jjdzr.console.Printable;
import com.isa.jjdzr.console.Printer;
import com.isa.jjdzr.console.Service;
import com.isa.jjdzr.dto.Wallet;
import com.isa.jjdzr.market.Market;

import java.util.*;

public class Menu  {
    private Printable printer = new Printer();
    private Service menuService = new MenuService();
    private final List<String> options = new ArrayList<>(
            List.of("1. Utwórz portfel.",
            "2. Wczytaj portfel",
            "3. Zapisz portfel",
            "4. Wyświetl portfel",
            "5. Dokonaj zakupu",
            "6. Dokonaj sprzedaży",
            "7. Dodaj gotówkę",
            "8. Kursy walut",
            "9. Zakończ"));

    public Menu() {
    }

    public void startMenu() {
        boolean keepWorking = true;
        Wallet wallet = null;
        Market market = new Market();
        menuService.clearScreen();
        printer.printActualLine("Witamy w portfelu inwestycyjnym !!!");

        while(keepWorking) {
            printer.printActualLine("\nUżytkownik: Guest\n"); //bedzie wyświetlać nazwę użytkownika
            printer.printMenuOptions(options);
            int option = getOptionNumber();
            switch(option) {
                case 1:
                    if (isWalletNull(wallet)) {
                        menuService.clearScreen();
                        wallet = new WalletGenView().start(wallet);
                    } else {
                        printer.printError("Masz już wczytany portfel, nie możesz utworzyć nowego.");
                    }
                    menuService.cont();
                    menuService.clearScreen();
                    break;
                case 2:
                    menuService.clearScreen();
                    wallet = new LoadViewer().load();
                    menuService.cont();
                    menuService.clearScreen();
                    break;
                case 3:
                    if (isWalletNull(wallet)) {
                        printer.printError("Nie można zapisać nieistniejącego portfela!");
                    } else {
                        new SaveViewer().save(wallet);
                    }
                    menuService.cont();
                    menuService.clearScreen();
                    break;
                case 4:
                    if (isWalletNull(wallet)) {
                        printer.printError("Brak portfela do wyświetlenia. Wczytaj lub utwórz nowy.");
                    } else {
                        menuService.clearScreen();
                        new WalletViewer().startViewer(wallet);
                    }
                    menuService.cont();
                    menuService.clearScreen();
                    break;
                case 5:
                    if (isWalletNull(wallet)) {
                        printer.printError("Nie można wykonać operacji na nieistniejącym portfelu!");
                    } else {
                        menuService.clearScreen();
                        new BrokerBuy().buy(wallet);
                    }
                    menuService.cont();
                    menuService.clearScreen();
                    break;
                case 6:
                    if (isWalletNull(wallet)) {
                        printer.printError("Nie można wykonać operacji na nieistniejącym portfelu!");
                    } else {
                        menuService.clearScreen();
                        new BrokerSell().sell(wallet);
                    }
                    menuService.cont();
                    menuService.clearScreen();
                    break;
                case 7:
                    if (isWalletNull(wallet)) {
                        printer.printError("Nie można wykonać operacji na nieistniejącym portfelu!");
                    } else {
                        menuService.clearScreen();
                        new WalletGenView().addCash(wallet);
                    }
                    menuService.cont();
                    menuService.clearScreen();
                    break;
                case 8:
                    menuService.clearScreen();
                    new ApiNbp().printExchangeRates();
                    menuService.cont();
                    menuService.clearScreen();
                    break;
                case 9: keepWorking = false; break;
                default:
                    break;
            }
        }
        menuService.clearScreen();
        printer.printActualLine("Do widzenia!!!");
    }
    //TODO: remake it as multipurpose and put in other class
    private int getOptionNumber() {
        Scanner scan = new Scanner(System.in);
        Integer num = null;
        printer.printActualLine("Podaj opcję, którą chcesz wybrać: ");
        while (num == null) {
            try {
                num = scan.nextInt();
                if (num > 9 || num < 1) {
                    printer.printError("Nie ma takiej opcji, spróbuj ponownie: ");
                    num = null;
                }
            } catch (InputMismatchException e) {
                printer.printError("Zła wartość, spróbuj ponownie: ");
                scan.next();
            }
        }
        return num;
    }
    //TODO: put this in other class (validator)
    private boolean isWalletNull(Wallet wallet) {
        return wallet == null;
    }

}
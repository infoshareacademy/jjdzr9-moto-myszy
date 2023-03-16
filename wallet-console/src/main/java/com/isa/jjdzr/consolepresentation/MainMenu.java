package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.api.ApiNbp;
import com.isa.jjdzr.console.MenuService;
import com.isa.jjdzr.console.Printable;
import com.isa.jjdzr.console.Printer;
import com.isa.jjdzr.console.Service;

import java.util.ArrayList;
import java.util.List;

public class MainMenu implements Menu {
    private final Printable printer;
    private final Service menuService;
    private final Long userId;
    private final List<String> options;

    public MainMenu() {
        this.printer = new Printer();
        this.menuService = new MenuService();
        this.userId  = 0L;
        this.options = new ArrayList<>(
                List.of("1. Utwórz portfel.",
                        "2. Wczytaj portfel",
                        "3. Zapisz portfel",
                        "4. Wyświetl portfel",
                        "5. Dokonaj zakupu",
                        "6. Dokonaj sprzedaży",
                        "7. Dodaj gotówkę",
                        "8. Kursy walut",
                        "9. Zakończ"));
    }
    @Override
    public void startMenu() {

        boolean keepWorking = true;
        Long walletId = null;

        menuService.clearScreen();
        printer.printActualLine("Witamy w portfelu inwestycyjnym !!!");

        while(keepWorking) {
            printer.printActualLine("\nUżytkownik: Guest\n"); //bedzie wyświetlać nazwę użytkownika
            printer.printMenuOptions(options);
            switch(menuService.getMenuOption(options.size())) {
                case 1:
                    menuService.clearScreen();
                    walletId = new WalletGenView().start(walletId);
                    menuService.cont();
                    menuService.clearScreen();
                    break;
                case 2:
                    menuService.clearScreen();
                    walletId = new LoadViewer().load(userId);
                    menuService.cont();
                    menuService.clearScreen();
                    break;
                case 3: //TODO: this go out, will make auto save
                    if (isWalletNull(walletId)) {
                        printer.printError("Nie można zapisać nieistniejącego portfela!");
                    } else {
                        new SaveViewer().save(walletId);
                    }
                    menuService.cont();
                    menuService.clearScreen();
                    break;
                case 4: //TODO: remake (later, after refactoring brokers)
                    if (isWalletNull(walletId)) {
                        printer.printError("Brak portfela do wyświetlenia. Wczytaj lub utwórz nowy.");
                    } else {
                        menuService.clearScreen();
                        walletId = new WalletViewer().startViewer(walletId);
                    }
                    menuService.cont();
                    menuService.clearScreen();
                    break;
                    if (isWalletNull(walletId)) {
                        printer.printError("Nie można wykonać operacji na nieistniejącym portfelu!");
                    } else {
                        menuService.clearScreen();
                        walletId = new BrokerBuy().buy(walletId);
                    }
                    menuService.cont();
                    menuService.clearScreen();
                    break;
                case 6: //TODO: refactor
                    if (isWalletNull(walletId)) {
                        printer.printError("Nie można wykonać operacji na nieistniejącym portfelu!");
                    } else {
                        menuService.clearScreen();
                        walletId = new BrokerSell().sell(walletId);
                    }
                    menuService.cont();
                    menuService.clearScreen();
                    break;
                case 7:
                    if (isWalletNull(walletId)) {
                        printer.printError("Nie można wykonać operacji na nieistniejącym portfelu!");
                    } else {
                        menuService.clearScreen();
                        new WalletGenView().topUpWallet(walletId);
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

    //TODO: put this in other class
    private boolean isWalletNull(Long walletId) {
        return walletId == null;
    }

}
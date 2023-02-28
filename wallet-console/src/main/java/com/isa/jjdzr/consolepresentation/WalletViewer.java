package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.console.MenuService;
import com.isa.jjdzr.console.Printable;
import com.isa.jjdzr.console.Printer;
import com.isa.jjdzr.console.Service;
import com.isa.jjdzr.dto.Wallet;

import java.util.*;
import java.util.regex.Pattern;

public class WalletViewer {
    private Printable printer = new Printer();
    private Service menuService = new MenuService();
    private AssetsViewer assetsViewer = new AssetsViewer();
    private final List<String> options = new ArrayList<>(
            List.of("1. Wyświetl listę posiadanych aktywów",
            "2. Wyświetl wybrane aktywa",
            "3. Wyświetl wszystkie aktywa",
            "4. Wyświetl gotówkę",
            "5. Powrót do menu"));


    public void startViewer(Wallet wallet) {
        boolean keepWorking = true;

        while(keepWorking) {
            printer.printMenuOptions(options);
            switch(menuService.getMenuOption(options.size())) {
                case 1:
                    assetsViewer.printWalletList(wallet.getWallet());
                    menuService.cont();
                    break;
                case 2:
                    printSingleWalletAsset(wallet);
                    menuService.cont();
                    break;
                case 3:
                    assetsViewer.printWallet(wallet.getWallet());
                    menuService.cont();
                    break;
                case 4:
                    printer.printActualLine("Posiadana gotówka: " + wallet.getCash() + "PLN");
                    menuService.cont();
                    break;
                case 5:
                    keepWorking = false;
                    break;
                default:
                    printer.printActualLine("Nie ma takiej opcji.");
            }
        }
    }

    //TODO: remake this - three parts 1. validation if wallet is empty, 2.'getAssetToPrint' and 3. printing actual asset
    private void printSingleWalletAsset(Wallet wallet) {
        Scanner scan = new Scanner(System.in);
        if (wallet.getWallet().size() == 0) {
            printer.printActualLine("Brak aktywów do wyświetlenia");
        } else {
            int index = -1;
            printer.printActualLine("Który aktyw chcesz zobaczyć ?");

            while (checkWAIndex(wallet, index)) {
                try {
                    printer.printActualLine("Podaj wartość od 1 do " + wallet.getWallet().size());
                    index = scan.nextInt();
                } catch (InputMismatchException e) {
                    printer.printError("Zła wartość, spróbuj ponownie.");
                    scan.next();
                }
            }
            assetsViewer.printWalletAsset(wallet.getWallet().get(index - 1));
        }
    }
    //TODO: put this in other class like validator and remake it
    private boolean checkWAIndex (Wallet wallet, int i) {
        return !Pattern.matches("[1-" + wallet.getWallet().size() + "]", String.valueOf(i));
    }
}

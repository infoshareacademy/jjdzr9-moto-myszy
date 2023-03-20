package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.console.MenuService;
import com.isa.jjdzr.console.Printable;
import com.isa.jjdzr.console.Printer;
import com.isa.jjdzr.console.Service;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletcore.service.WalletAssetService;
import com.isa.jjdzr.walletcore.service.WalletService;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WalletViewer {
    private final Printable printer;
    private final Service menuService;
    private final AssetsViewer assetsViewer;
    private final List<String> options;
    private final WalletAssetService walletAssetService;
    private final WalletService walletService;

    public WalletViewer() {
        this.printer = new Printer();
        this.menuService = new MenuService();
        this.assetsViewer = new AssetsViewer();
        this.walletAssetService = new WalletAssetService();
        this.walletService = new WalletService();
        this.options = new ArrayList<>(
                List.of("1. Wyświetl listę posiadanych aktywów",
                        "2. Wyświetl wybrane aktywa",
                        "3. Wyświetl wszystkie aktywa",
                        "4. Wyświetl gotówkę",
                        "5. Powrót do menu"));
    }

    public Long startViewer(Long walletId) {
        boolean keepWorking = true;

        while(keepWorking) {
            printer.printMenuOptions(options);
            switch(menuService.getMenuOption(options.size())) {
                case 1:
                    assetsViewer.printWalletList(walletId);
                    menuService.cont();
                    break;
                case 2:
                    printSingleWalletAsset(walletId);
                    menuService.cont();
                    break;
                case 3:
                    assetsViewer.printWallet(walletId);
                    menuService.cont();
                    break;
                case 4:
                    printer.printActualLine("Posiadana gotówka: " + walletService.find(walletId).getCash() + "PLN");
                    menuService.cont();
                    break;
                case 5:
                    keepWorking = false;
                    break;
                default:
                    printer.printActualLine("Nie ma takiej opcji.");
            }
        }
        return walletId;
    }

    //TODO: remake this - three parts 1. validation if wallet is empty, 2.'getAssetToPrint' and 3. printing actual asset
    private void printSingleWalletAsset(Long walletId) {
        List<WalletAsset> wallet = walletAssetService.findWalletAssetsByWalletId(walletId);
        Scanner scan = new Scanner(System.in);
        if (wallet.size() == 0) {
            printer.printActualLine("Brak aktywów do wyświetlenia");
        } else {
            int index = -1;
            printer.printActualLine("Który aktyw chcesz zobaczyć ?");

            while (checkWAIndex(wallet.size(), index)) {
                try {
                    printer.printActualLine("Podaj wartość od 1 do " + wallet.size());
                    index = scan.nextInt();
                } catch (InputMismatchException e) {
                    printer.printError("Zła wartość, spróbuj ponownie.");
                    scan.next();
                }
            }
            WalletAsset walletAsset = wallet.get(index - 1);
            walletAssetService.findCurrentPrice(walletAsset.getId());
            assetsViewer.printWalletAsset(walletAsset);
        }
    }
    //TODO: put this in other class like validator and remake it
    private boolean checkWAIndex (int walletSize, int i) {
        return !Pattern.matches("[1-" + walletSize + "]", String.valueOf(i));
    }
}

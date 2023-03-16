
package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.brokerlogic.BrokerLogicSell;
import com.isa.jjdzr.console.Printer;
import com.isa.jjdzr.dto.WalletAsset;
import com.isa.jjdzr.service.WalletAssetService;
import com.isa.jjdzr.service.WalletService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class BrokerSell {
    private final Printer printer;
    private final WalletService walletService;
    private final WalletAssetService walletAssetService;

    public BrokerSell() {
        this.printer = new Printer();
        this.walletService = new WalletService();
        this.walletAssetService = new WalletAssetService();
    }

    public Long sell(Long walletId) {
        if (walletService.findWalletAssets(walletId).size() == 0) {
            printer.printActualLine("Brak aktywów do sprzedania.");
            printer.printActualLine("Powrót do menu.");
        } else {
            Long waIndex = getWalletAssetIndex(walletId);
            String quantity = getQuantityToSell(waIndex);
            BigDecimal currentPrice = walletAssetService.getCurrentPrice(waIndex);
            printer.printActualLine("Aktualna cena pojedynczej sztuki: " + currentPrice + "PLN");
            new BrokerLogicSell().sell(walletId, waIndex, quantity, currentPrice);
            printer.printActualLine("Sprzedaż zakończona sukcesem.");
            printer.printActualLine("Portfel zasilono kwotą: " + new BigDecimal(quantity).multiply(currentPrice) + "PLN");
        }
        return walletId;
    }

    private Long getWalletAssetIndex(Long walletId) {

        Scanner scanner = new Scanner(System.in);
        List<WalletAsset> walletAssets = walletAssetService.findWalletAssetsByWalletId(walletId);
        int walletAssetCount = walletAssets.size();

        System.out.println("Jaki aktyw chcesz sprzedać?");
        //TODO: put this in asset vierwer as printWalletAssetsList
        int i = 1;
        for (WalletAsset wa : walletAssets) {
            printer.printActualLine(i++ + ". " + wa.getAssetId());
        }
        //TODO: create method from this
        int walletAssetIndex = -1;
        while (walletAssetIndex < 0 || walletAssetIndex >= walletAssetCount) {
            printer.printActualLine("Wybierz opcję pomiędzy 1, a " + walletAssetCount + ": ");
            try {
                walletAssetIndex = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (NumberFormatException e) {
                printer.printActualLine("Błąd. Proszę, wprowadź liczbę.");
            }

            if (walletAssetIndex < 0 || walletAssetIndex >= walletAssetCount) {
                printer.printActualLine("Błąd. Wybierz opcję pomiędzy 1, a  " + walletAssetCount + ".");
            }
        }
        return (long) walletAssetIndex;
    }
    //FIXME: make working validation
    private String getQuantityToSell(Long index) {

        Scanner input = new Scanner(System.in);
        String quantity;
        BigDecimal quantityToSell;
        WalletAsset asset = walletAssetService.find(index);
        printer.printActualLine("Posiadana ilość: " + asset.getQuantity());

        while (true) {
            printer.printActualLine("Podaj ilość, którą chcesz sprzedać: ");
            quantity = input.nextLine();

            if (quantity.matches("[0-9]*")) {
                quantityToSell = new BigDecimal(quantity);

                if (quantityToSell.compareTo(asset.getQuantity()) <= 0) {
                    return quantity;
                } else {
                    printer.printActualLine("Nie masz tyle aktywów do sprzedania");
                }
            } else {
                printer.printActualLine("Nieprawidłowe dane. Wprowadź ponownie.");
            }
        }
    }
}


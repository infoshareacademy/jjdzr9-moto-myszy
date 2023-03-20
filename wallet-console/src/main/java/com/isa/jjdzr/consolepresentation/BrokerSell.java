
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
    private final AssetsViewer assetsViewer;
    private final Scanner scan;

    public BrokerSell() {
        this.printer = new Printer();
        this.walletService = new WalletService();
        this.walletAssetService = new WalletAssetService();
        this.assetsViewer = new AssetsViewer();
        this.scan = new Scanner(System.in);
    }

    public Long sell(Long walletId) {
        if (walletService.findWalletAssets(walletId).size() == 0) {
            printer.printActualLine("Brak aktywów do sprzedania.");
            printer.printActualLine("Powrót do menu.");
        } else {
            Long waIndex = getWalletAssetIndex(walletId);
            WalletAsset walletAsset = walletAssetService.findCurrentPrice(waIndex);
            BigDecimal currentPrice = walletAsset.getCurrentPrice();
            printer.printActualLine("Aktualna cena pojedynczej sztuki: " + currentPrice + "PLN");
            String quantity = getQuantityToSell(waIndex);
            new BrokerLogicSell().sell(walletId, waIndex, quantity);
            printer.printActualLine("Sprzedaż zakończona sukcesem.");
            printer.printActualLine("Portfel zasilono kwotą: " + new BigDecimal(quantity).multiply(currentPrice) + "PLN");
        }
        return walletId;
    }

    private Long getWalletAssetIndex(Long walletId) {
        System.out.println("Jaki aktyw chcesz sprzedać?");
        assetsViewer.printWalletAssetsList(walletId);
        return getIndexFromUserInput(walletId);
    }



    private Long getIndexFromUserInput(Long walletId) {
        List<WalletAsset> walletAssets = walletAssetService.findWalletAssetsByWalletId(walletId);
        long walletAssetCount = walletAssets.size();
        long result = -1;
        while (result < 0 || result >= walletAssetCount) {
            printer.printActualLine("Wybierz opcję pomiędzy 1, a " + walletAssetCount + ": ");
            try {
                result = Integer.parseInt(scan.nextLine()) - 1L;
            } catch (NumberFormatException e) {
                printer.printActualLine("Błąd. Proszę, wprowadź liczbę.");
            }
            if (result < 0 || result >= walletAssetCount) {
                printer.printActualLine("Błąd. Wybierz opcję pomiędzy 1, a  " + walletAssetCount + ".");
            }
        }
        return result;
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


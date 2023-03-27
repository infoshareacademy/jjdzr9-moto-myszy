
package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.brokerlogic.BrokerLogicSell;
import com.isa.jjdzr.console.Printer;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletcore.service.WalletAssetServiceImpl;
import com.isa.jjdzr.walletcore.service.WalletServiceImpl;


import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class BrokerSell {
    private final Printer printer;
    private final WalletServiceImpl walletServiceImpl;
    private final WalletAssetServiceImpl walletAssetServiceImpl;
    private final AssetsViewer assetsViewer;
    private final Scanner scan;

    public BrokerSell() {
        this.printer = new Printer();
        this.walletServiceImpl = new WalletServiceImpl();
        this.walletAssetServiceImpl = new WalletAssetServiceImpl();
        this.assetsViewer = new AssetsViewer();
        this.scan = new Scanner(System.in);
    }

    public Long sell(Long walletId) {
        if (walletServiceImpl.findWalletAssets(walletId).size() == 0) {
            printer.printActualLine("Brak aktywów do sprzedania.");
            printer.printActualLine("Powrót do menu.");
        } else {
            Long waIndex = getWalletAssetIndex(walletId);
            WalletAsset walletAsset = walletAssetServiceImpl.findCurrentPrice(waIndex);
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
        List<WalletAsset> walletAssets = walletAssetServiceImpl.findWalletAssetsByWalletId(walletId);
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
        WalletAsset asset = walletAssetServiceImpl.find(index);
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


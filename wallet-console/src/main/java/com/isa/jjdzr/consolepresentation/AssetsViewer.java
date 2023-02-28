package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.console.Printable;
import com.isa.jjdzr.console.Printer;
import com.isa.jjdzr.dto.Asset;
import com.isa.jjdzr.dto.WalletAsset;
import com.isa.jjdzr.market.Market;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class AssetsViewer {
    private Printable printer = new Printer();

    public void printAsset(Asset asset) {
        printer.printActualLine("Asset ID: " + asset.getId());
        printer.printActualLine("Current Price: " + asset.getCurrentPrice());
        printer.printEmptyLine();
    }

    public void printWalletAsset(WalletAsset walletAsset) {
        printer.printActualLine("ID: " + walletAsset.getId());
        printer.printActualLine("Cena zakupu: " + walletAsset.getPurchasePrice() + "PLN");
        printer.printActualLine("Ilość: " + walletAsset.getPurchasedQuantity());
        //TODO:
        printer.printActualLine("Wartość w momencie zakupu: " +walletAsset.getPurchasedQuantity().multiply(walletAsset.getPurchasePrice()) + "PLN");
        BigDecimal cp = walletAsset.getCurrentPrice(new Market());
        printer.printActualLine("Aktualna cena: " + cp + "PLN");
        printer.printActualLine("Aktualna wartość: " + cp.multiply(walletAsset.getPurchasedQuantity()) + "PLN");
        printer.printEmptyLine();
    }

    public void printAssetToBuyList(ArrayList<Asset> assetList) {
        printer.printActualLine("Lista aktyw do kupna:");
        int i = 0;
        for (Asset asset : assetList) {
            printer.printActualLine(i++ + ". " +asset.getId() + " - " + asset.getCurrentPrice());
            printer.printEmptyLine();
            }
    }

    public void printWallet(List<WalletAsset> wallet) {
        if (wallet.size() == 0) {
            printer.printActualLine("Brak aktywów do wyświetlenia");
        } else {
            printer.printActualLine("W skład Twojego portfela wchodzą:");
            for (WalletAsset walletAsset : wallet) {
                printWalletAsset(walletAsset);
            }
        }
    }

    public void printWalletList(List<WalletAsset> wallet) {
        if (wallet.size() == 0) {
            printer.printActualLine("Brak aktywów do wyświetlenia");
        } else {
            int i = 0;
            printer.printActualLine("Lista twoich aktyw: ");
            printer.printEmptyLine();
            for (WalletAsset walletAsset : wallet) {
                printer.printActualLine(i++ + ". " + walletAsset.getId());
            }
        }
        printer.printEmptyLine();
    }
}
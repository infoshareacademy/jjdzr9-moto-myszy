package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.console.Printable;
import com.isa.jjdzr.console.Printer;
import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletcore.service.WalletAssetServiceImpl;

import java.util.List;


public class AssetsViewer {
    private final Printable printer;
    private final WalletAssetServiceImpl walletAssetServiceImpl;

    public AssetsViewer() {
        this.printer = new Printer();
        this.walletAssetServiceImpl = new WalletAssetServiceImpl();
    }

    public void printAsset(Asset asset) {
        printer.printActualLine("Asset ID: " + asset.getId());
        printer.printActualLine("Current Price: " + asset.getCurrentPrice());
        printer.printEmptyLine();
    }

    public void printWalletAsset(WalletAsset walletAsset) {
        printer.printActualLine("ID: " + walletAsset.getAssetId());
        printer.printActualLine("Cena zakupu: " + walletAsset.getPurchasePrice() + "PLN");
        printer.printActualLine("Ilość: " + walletAsset.getQuantity());
        printer.printActualLine("Wartość w momencie zakupu: " + walletAsset.getQuantity().multiply(walletAsset.getPurchasePrice()) + "PLN");
        printer.printActualLine("Aktualna cena: " + walletAsset.getCurrentPrice() + "PLN");
        printer.printActualLine("Aktualna wartość: " + walletAsset.getCurrentPrice().multiply(walletAsset.getQuantity()) + "PLN");
        printer.printEmptyLine();
    }

    public void printAssetsToBuyList(List<Asset> assetList) {
        printer.printActualLine("Lista aktyw do kupna:");
        int i = 0;
        for (Asset asset : assetList) {
            printer.printActualLine(i++ + ". " + asset.getId() + " - " + asset.getCurrentPrice());
            printer.printEmptyLine();
        }
    }

    public void printWallet(Long walletId) {
        List<WalletAsset> wallet = walletAssetServiceImpl.findWalletAssetsByWalletId(walletId);
        if (wallet.size() == 0) {
            printer.printActualLine("Brak aktywów do wyświetlenia");
        } else {
            printer.printActualLine("W skład Twojego portfela wchodzą:");
            for (WalletAsset walletAsset : wallet) {
                walletAssetServiceImpl.find(walletAsset.getId());
                printWalletAsset(walletAsset);
            }
        }
    }

    public void printWalletList(Long walletId) {
        List<WalletAsset> wallet = walletAssetServiceImpl.findWalletAssetsByWalletId(walletId);
        if (wallet.size() == 0) {
            printer.printActualLine("Brak aktywów do wyświetlenia");
        } else {
            int i = 0;
            printer.printActualLine("Lista twoich aktyw: ");
            printer.printEmptyLine();
            for (WalletAsset walletAsset : wallet) {
                printer.printActualLine(i++ + ". " + walletAsset.getAssetId());
            }
        }
        printer.printEmptyLine();
    }

    public void printWalletAssetsList(Long walletId) {
        List<WalletAsset> walletAssets = walletAssetServiceImpl.findWalletAssetsByWalletId(walletId);
        int i = 1;
        for (WalletAsset wa : walletAssets) {
            printer.printActualLine(i++ + ". " + wa.getAssetId());
        }
    }
}
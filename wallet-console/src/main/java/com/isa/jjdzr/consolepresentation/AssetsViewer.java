package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.dto.Asset;
import com.isa.jjdzr.dto.WalletAsset;
import com.isa.jjdzr.market.Market;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class AssetsViewer {

    public void printAsset(Asset asset) {
        System.out.println("Asset ID: " + asset.getId());
        System.out.println("Current Price: " + asset.getCurrentPrice());
        System.out.println();
    }

    public void printWalletAsset(WalletAsset walletAsset) {
        System.out.println("ID: " + walletAsset.getId());
        System.out.println("Cena zakupu: " + walletAsset.getPurchasePrice() + "PLN");
        System.out.println("Ilość: " + walletAsset.getPurchasedQuantity());
        System.out.println("Wartość w momencie zakupu: " +walletAsset.getPurchasedQuantity().multiply(walletAsset.getPurchasePrice()) + "PLN");
        BigDecimal cp = walletAsset.getCurrentPrice(new Market());
        System.out.println("Aktualna cena: " + cp + "PLN");
        System.out.println("Aktualna wartość: " + cp.multiply(walletAsset.getPurchasedQuantity()) + "PLN");
        System.out.println();
    }

    public void printAssetToBuyList(ArrayList<Asset> assetList) {
        System.out.println("Asset to buy list:");
        for (Asset asset : assetList) {
            System.out.println(asset.getId() + " - " + asset.getCurrentPrice());
            System.out.println();
        }
    }

    public void printWallet(List<WalletAsset> wallet) {
        if (wallet.size() == 0) {
            System.out.println("Brak aktywów do wyświetlenia");
        } else {
            System.out.println("W skład Twojego portfela wchodzą:");
            for (WalletAsset walletAsset : wallet) {
                printWalletAsset(walletAsset);
            }
        }
    }

    public void printWalletList(List<WalletAsset> wallet) {
        if (wallet.size() == 0) {
            System.out.println("Brak aktywów do wyświetlenia");
        } else {
            int i = 1;
            System.out.println("Lista twoich aktyw: ");
            for (WalletAsset walletAsset : wallet) {
                System.out.println(i + ". " + walletAsset.getId());
                i++;
            }
        }
        System.out.println();
    }
}
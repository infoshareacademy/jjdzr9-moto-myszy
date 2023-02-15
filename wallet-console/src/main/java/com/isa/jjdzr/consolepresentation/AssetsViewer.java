package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.dto.Asset;
import com.isa.jjdzr.dto.WalletAsset;

import java.util.ArrayList;



class AssetsViewer {

    public void printAsset(Asset asset) {
        System.out.println("Asset ID: " + asset.getId());
        System.out.println("Current Price: " + asset.getCurrentPrice());
    }

    public void printWalletAsset(WalletAsset walletAsset) {
        System.out.println("ID: " + walletAsset.getId());
        System.out.println("purchasePrice: " + walletAsset.getPurchasePrice());
        System.out.println("purchasedQuantity: " + walletAsset.getPurchasedQuantity());
    }

    public void printAssetToBuyList(ArrayList<Asset> assetList) {
        System.out.println("Asset to buy list:");
        for (Asset asset : assetList) {
            System.out.println(asset.getId() + " - " + asset.getCurrentPrice());
        }
    }

    public void printWallet(ArrayList<WalletAsset> wallet) {
        System.out.println("Wallet:");
        for (WalletAsset walletAsset : wallet) {
            printWalletAsset(walletAsset);
        }
    }
}
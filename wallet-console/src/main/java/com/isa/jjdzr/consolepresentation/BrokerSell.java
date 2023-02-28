
package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.brokerlogic.BrokerLogicBuy;
import com.isa.jjdzr.brokerlogic.BrokerLogicSell;
import com.isa.jjdzr.dto.Asset;
import com.isa.jjdzr.dto.Wallet;
import com.isa.jjdzr.dto.WalletAsset;
import com.isa.jjdzr.market.Market;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class BrokerSell {


    public void sell(Wallet wallet) {
        if (wallet.getWallet().size() == 0) {
            System.out.println("Brak aktywów do sprzedania.");
            System.out.println("Powrót do menu.");
        } else {
            int waIndex = getWalletAssetIndex(wallet);
            String quantity = getQuantityToSell(wallet, waIndex);
            BigDecimal currentPrice = wallet.getWallet().get(waIndex).getCurrentPrice(new Market());
            System.out.println("Aktualna cena pojedynczej sztuki: " + currentPrice + "PLN");
            new BrokerLogicSell().sell(wallet, waIndex, quantity, currentPrice);
            System.out.println("Sprzedaż zakończona sukcesem.");
            System.out.println("Portfel zasilono kwotą: " + new BigDecimal(quantity).multiply(currentPrice) + "PLN");
        }
    }

    private int getWalletAssetIndex(Wallet wallet) {

        Scanner scanner = new Scanner(System.in);
        List<WalletAsset> walletAsset = wallet.getWallet();
        int walletAssetCount = walletAsset.size();

        System.out.println("Jaki aktyw chcesz sprzedać?");
        for (int i = 0; i < walletAssetCount; i++) {
            System.out.println(i + 1 + ". " + wallet.getWallet().get(i).getId());
        }

        int walletAssetIndex = -1;
        while (walletAssetIndex < 0 || walletAssetIndex >= walletAssetCount) {
            System.out.print("Wybierz opcję pomiędzy 1, a " + walletAssetCount + ": ");
            try {
                walletAssetIndex = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Błąd. Proszę, wprowadź liczbę.");
            }

            if (walletAssetIndex < 0 || walletAssetIndex >= walletAssetCount) {
                System.out.println("Błąd. Wybierz opcję pomiędzy 1, a  " + walletAssetCount + ".");
            }
        }

        return walletAssetIndex;
    }
    //FIXME: make working validation
    private String getQuantityToSell(Wallet wallet, int index) {

        Scanner input = new Scanner(System.in);
        String quantity;
        BigDecimal quantityToSell;
        WalletAsset asset = wallet.getWallet().get(index);
        System.out.println("Posiadana ilość: " + asset.getPurchasedQuantity());

        while (true) {
            System.out.println("Podaj ilość, którą chcesz sprzedać: ");
            quantity = input.nextLine();

            if (quantity.matches("[0-9]*")) {
                quantityToSell = new BigDecimal(quantity);

                if (quantityToSell.compareTo(asset.getPurchasedQuantity()) <= 0) {
                    return quantity;
                } else {
                    System.out.println("Nie masz tyle aktywów do sprzedania");
                }
            } else {
                System.out.println("Nieprawidłowe dane. Wprowadź ponownie.");
            }
        }

    }

    private boolean checkQuantity(Wallet wallet, int index, String quantity) {

        BigDecimal qty = new BigDecimal(quantity);
        WalletAsset asset = wallet.getWallet().get(index);
        BigDecimal currentQuantity = asset.getPurchasedQuantity();

        if (qty.compareTo(currentQuantity) <= 0) {
            return true;
        } else {
            return false;
        }

    }

    private boolean checkStringInput(String str) {

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;

    }
}


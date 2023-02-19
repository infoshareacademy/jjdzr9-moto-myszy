
package com.isa.jjdzr.consolepresentation;
import com.isa.jjdzr.brokerlogic.BrokerLogicBuy;
import com.isa.jjdzr.brokerlogic.BrokerLogicSell;
import com.isa.jjdzr.dto.Asset;
import com.isa.jjdzr.dto.WalletAsset;
import com.isa.jjdzr.market.Market;
import com.isa.jjdzr.dto.Wallet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

    class Broker {
        public void buy(Wallet wallet) {

            Asset asset = getAsset(new Market());
            String quantity = getQuantityToBuy(wallet, asset);
            new BrokerLogicBuy().buy(asset, wallet,quantity);
            System.out.println("Zapłaciłeś: " + asset.getCurrentPrice().multiply(new BigDecimal(quantity)) + "PLN");

        }

        private Asset getAsset(Market market) {

            Scanner scanner = new Scanner(System.in);
            List<Asset> assets = market.availableAssets();
            int assetCount = assets.size();

            System.out.println("Jaki aktyw chcesz kupić?");
            for (int i = 0; i < assetCount; i++) {
                System.out.println(i + 1 + ". " + assets.get(i).getId() + "\nAktualna cena: " + assets.get(i).getCurrentPrice());
            }

            int assetIndex = -1;
            while (assetIndex < 0 || assetIndex >= assetCount) {
                System.out.print("Wybierz opcję pomiędzy 1, a " + assetCount + ": ");
                try {
                    assetIndex = Integer.parseInt(scanner.nextLine()) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Błąd. Proszę, wprowadź liczbę.");
                }

                if (assetIndex < 0 || assetIndex >= assetCount) {
                    System.out.println("Błąd. Wybierz opcję pomiędzy 1, a  " + assetCount + ".");
                }
            }

            return assets.get(assetIndex);

        }

        private String getQuantityToBuy(Wallet wallet, Asset asset) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Posiadane środki: " + wallet.getCash() +"PLN");
            System.out.print("Podaj ilość jaką chcesz kupić: ");
            String input = sc.nextLine();

            while (!input.matches("[0-9]*")) {
                System.out.println("Błąd. Wprowadź cyfrę.");
                input = sc.nextLine();
            }

            int quantity = Integer.parseInt(input);
            BigDecimal cost = new BigDecimal(quantity).multiply(asset.getCurrentPrice());

            if (cost.compareTo(wallet.getCash()) < 0) {
                System.out.println("Gratulacje, właśnie dokonałeś zakupu");
            } else if (cost.compareTo(wallet.getCash()) == 0) {
                System.out.println("Gratulacje, właśnie dokonałeś zakupu");
            } else {
                System.out.println("Niewystarczająca ilość środków w portfelu. Proszę, podaj mniejszą ilość.");
                return getQuantityToBuy(wallet, asset);
            }
            return input;

        }

        private boolean checkCash(Wallet wallet, BigDecimal cost){
            return wallet.getCash().compareTo(cost) >= 0;
        }

        public void sell(Wallet wallet) {
            int waIndex = getWalletAssetIndex(wallet);
            String quantity = getQuantityToSell(wallet, waIndex);
            BigDecimal currentPrice = wallet.getWallet().get(waIndex).getCurrentPrice(new Market());
            System.out.println("Aktualna cena pojedynczej sztuki: " + currentPrice + "PLN");
            new BrokerLogicSell().sell(wallet, waIndex,quantity, currentPrice);
            System.out.println("Sprzedaż zakończona sukcesem.");
            System.out.println("Portfel zasilono kwotą: " + new BigDecimal(quantity).multiply(currentPrice) + "PLN");
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


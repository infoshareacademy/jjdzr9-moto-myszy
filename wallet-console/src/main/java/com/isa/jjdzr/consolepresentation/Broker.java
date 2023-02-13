
package com.isa.jjdzr.consolepresentation;
import com.isa.jjdzr.dto.Asset;
import com.isa.jjdzr.dto.WalletAsset;
import com.isa.jjdzr.market.Market;
import com.isa.jjdzr.dto.Wallet;

import javax.management.MXBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    class Broker {
        public void buy() {
            Scanner scanner = new Scanner(System.in);
            getAssetIndex(new Market());

        /* ma pobierać jaki aktyw chcemy kupić
         ma pobierać ile chcemy go kupić
         sprawdzic czy wystarczy nam kasy na zakup
         na podstawie tych danych tworzy nowy obiekt WalletAsset
         dodaje ten obiekt do Wallet*/
        }

        private int getAssetIndex(Market market) {

            //Łukasz
            //ma pobierać od usera jaki aktyw chcemy kupić
            //walidacja !


            Scanner scanner = new Scanner(System.in);
            List<Asset> assets = market.availableAssets();
            int assetCount = assets.size();

            System.out.println("Jaki aktyw chcesz kupić?");
            for (int i = 0; i < assetCount; i++) {
                System.out.println(i + 1 + ". " + assets.get(i).getId());
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

            return assetIndex;

        }

        private String getQuantityToBuy(Wallet wallet, Asset asset) {
            //Łukasz
        /* ma pobierać ile chcemy go kupić
         sprawdza czy wprowadzone dane mają tylko 0-9 i .
         sprawdzic czy wystarczy nam kasy na zakup*/

            Scanner sc = new Scanner(System.in);
            System.out.print("Podaj ilość jaką chcesz kupić: ");
            String input = sc.nextLine();

            while (!input.matches("[0-9]")) { //?
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

        public void sell() {
        /*ma pobierać jaki aktyw chcemy sprzedać
        ma pobierać ile chcemy go sprzedać
        sprawdzić czy mamy taką ilość w Wallet
        na podstawie tych danych modyfikuje nam obiekt WalletAsset wyciągnięty z listy
        dodaje gotówkę (ilość sprzedana * aktualna cena)*/
        }

        private int getWalletAssetIndex(Wallet wallet) {

            //Łukasz
            //ma pobierać od usera jaki aktyw chcemy sprzedać

            Scanner scanner = new Scanner(System.in);
            List<WalletAsset> walletAsset = wallet.getWallet();
            int walletAssetCount = walletAsset.size();

            System.out.println("Jaki aktyw chcesz kupić?");
            for (int i = 0; i < walletAssetCount; i++) {
                System.out.println(i + 1 + ". " + wallet.getWallet().get(i).getId());
            }

            int walletAssetIndex = -1;
            while (walletAssetIndex < 0 || walletAssetIndex >= walletAssetCount) {
                System.out.print("Wybierz opcję pomiędzy 1, a " + walletAssetIndex + ": ");
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

            //Łukasz
        /* ma pobierać ile chcemy go sprzedać
         sprawdza czy wprowadzone dane mają tylko 0-9 i .
        sprawdzić czy mamy taką ilość w Wallet*/

            Scanner input = new Scanner(System.in);
            String quantity;
            BigDecimal quantityToSell;
            WalletAsset asset = wallet.getWallet().get(index);
            System.out.print("Podaj ilość jaką chcesz sprzedać: ");

            while (true) {
                System.out.println("Podaj ilość, którą chcesz sprzedać: ");
                quantity = input.nextLine();

                if (quantity.matches("[0-9]")) {
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
            //Łukasz
            //sprawdzić czy mamy taką ilość w Wallet
            
            BigDecimal qty = new BigDecimal(quantity);
            WalletAsset asset = wallet.getWallet().get(index);
            BigDecimal currentQuantity = asset.getPurchasedQuantity();

            if (qty.compareTo(currentQuantity) <= 0) {
                return true;
            } else {
                return false;
            }
            
            //Łukasz
            //sprawdzić czy mamy taką ilość w Wallet
        }

        private boolean checkStringInput(String str) {

            //Łukasz
            // sprawdza czy wprowadzone dane mają tylko 0-9 i .


            for (char c : str.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;

        }
    }


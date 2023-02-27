
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

public class BrokerBuy {
    public void buy(Wallet wallet) {

        Asset asset = getAsset(new Market());
        String quantity = getQuantityToBuy(wallet, asset);
        new BrokerLogicBuy().buy(asset, wallet,quantity);
        System.out.println("Zapłaciłeś: " + asset.getCurrentPrice().multiply(new BigDecimal(quantity)) + "PLN");

    }
    //FIXME:
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
    //FIXME:
    private String getQuantityToBuy(Wallet wallet, Asset asset) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Posiadane środki: " + wallet.getCash() +"PLN");
        System.out.print("Podaj ilość jaką chcesz kupić: ");
        String input = sc.nextLine();

        while (!input.matches("[0-9]*")) {
            System.out.println("Błąd. Wprowadź cyfrę.");
            input = sc.nextLine();
        }

        try {
            int quantity = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("Zła wartość, spróbuj ponownie.");
            return getQuantityToBuy(wallet, asset);
        }
        BigDecimal cost = new BigDecimal(input).multiply(asset.getCurrentPrice());

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


}


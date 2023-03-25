
package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.brokerlogic.BrokerLogicBuy;
import com.isa.jjdzr.console.Printable;
import com.isa.jjdzr.console.Printer;
import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletcore.market.Market;
import com.isa.jjdzr.walletcore.service.WalletServiceImpl;


import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class BrokerBuy {
    private final Printable printer;
    private final AssetsViewer assetsViewer;
    private final WalletServiceImpl walletServiceImpl;

    public BrokerBuy() {
        this.printer = new Printer();
        this.assetsViewer = new AssetsViewer();
        this.walletServiceImpl = new WalletServiceImpl();
    }

    public Long buy(Long walletId) {

        Asset asset = getAsset(new Market());
        String quantity = getQuantityToBuy(walletId, asset);
        printer.printActualLine("Zapłaciłeś: " + asset.getCurrentPrice().multiply(new BigDecimal(quantity)) + "PLN");
        return new BrokerLogicBuy().buy(asset, walletId, quantity);

    }

    //FIXME: make working validation
    private Asset getAsset(Market market) {

        Scanner scanner = new Scanner(System.in);
        List<Asset> assets = market.availableAssets();
        int assetCount = assets.size();

        printer.printActualLine("Jaki aktyw chcesz kupić?");
        assetsViewer.printAssetsToBuyList(assets);

        int assetIndex = -1;
        while (assetIndex < 0 || assetIndex >= assetCount) {
            printer.printActualLine("Wybierz opcję pomiędzy 1, a " + assetCount + ": ");
            try {
                assetIndex = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (NumberFormatException e) {
                printer.printError("Błąd. Proszę, wprowadź liczbę.");
            }

            if (assetIndex < 0 || assetIndex >= assetCount) {
                printer.printError("Błąd. Wybierz opcję pomiędzy 1, a  " + assetCount + ".");
            }
        }

        return assets.get(assetIndex);

    }

    //FIXME: repair validation
    private String getQuantityToBuy(Long walletId, Asset asset) {
        Wallet wallet = walletServiceImpl.find(walletId);
        Scanner sc = new Scanner(System.in);
        printer.printActualLine("Posiadane środki: " + wallet.getCash() + "PLN");
        printer.printActualLine("Podaj ilość jaką chcesz kupić: ");
        String quantityToBuy = sc.nextLine();

        while (!quantityToBuy.matches("[0-9]*")) {
            printer.printError("Błąd. Wprowadź cyfrę.");
            quantityToBuy = sc.nextLine();
        }

        try {
            Integer.parseInt(quantityToBuy);
        } catch (Exception e) {
            printer.printError("Zła wartość, spróbuj ponownie.");
            return getQuantityToBuy(walletId, asset);
        }
        BigDecimal cost = new BigDecimal(quantityToBuy).multiply(asset.getCurrentPrice());

        if (cost.compareTo(wallet.getCash()) < 0) {
            printer.printActualLine("Gratulacje, właśnie dokonałeś zakupu");
        } else if (cost.compareTo(wallet.getCash()) == 0) {
            printer.printActualLine("Gratulacje, właśnie dokonałeś zakupu");
        } else {
            printer.printActualLine("Niewystarczająca ilość środków w portfelu. Proszę, podaj mniejszą ilość.");
            return getQuantityToBuy(walletId, asset);
        }
        return quantityToBuy;
    }
}


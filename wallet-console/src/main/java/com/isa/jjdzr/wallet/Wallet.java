package com.isa.jjdzr.wallet;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Wallet {
    private ArrayList<WalletAsset> wallet;
    private BigDecimal cash;

    public Wallet() {
        this.wallet = new ArrayList<>();
        this.cash = new BigDecimal(0);
    }

    public void addAsset(WalletAsset asset) {
        wallet.add(asset);
    }

    public void addCash(String cash) {
        this.cash = this.cash.add(new BigDecimal(cash));
    }

    public void addCash(BigDecimal cash) {
        this.cash = this.cash.add(cash);
    }

    public void spendCash(BigDecimal cash) {
        this.cash = this.cash.subtract(cash);
    }

    public ArrayList<WalletAsset> getWallet() {
        return wallet;
    }

    public BigDecimal getCash() {
        return cash;
    }
}

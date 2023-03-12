package com.isa.jjdzr.dto;

import com.isa.jjdzr.common.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Data
public class Wallet implements BaseEntity {
    private Long id;
    private Long userId;
    private String walletName;
    private List<WalletAsset> wallet;
    private BigDecimal cash;

    public Wallet() {
        this.wallet = new ArrayList<>();
        this.cash = new BigDecimal(0);
    }
    //TODO: put those in wallet service
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

    public List<WalletAsset> getWallet() {
        return wallet;
    }

    public BigDecimal getCash() {
        return cash;
    }
}

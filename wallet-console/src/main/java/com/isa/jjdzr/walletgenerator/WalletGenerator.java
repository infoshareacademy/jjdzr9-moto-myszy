package com.isa.jjdzr.walletgenerator;

import com.isa.jjdzr.dto.Wallet;

public class WalletGenerator {
    public Wallet generateWallet(String cash) {
        Wallet wallet = new Wallet();
        wallet.addCash(cash);
        return wallet;
    }
}



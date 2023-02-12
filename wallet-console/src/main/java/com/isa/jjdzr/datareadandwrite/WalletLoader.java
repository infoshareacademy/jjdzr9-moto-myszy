package com.isa.jjdzr.datareadandwrite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.jjdzr.dto.Wallet;
import com.isa.jjdzr.dto.WalletAsset;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WalletLoader {
    public Wallet loadWallet(){

        ObjectMapper objectMapper = new ObjectMapper();
        WalletAsset[] wa;

        Path path = Path.of("src","main","resources", "walletassets.txt");
        String s = null;
        try {
            s = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            wa = objectMapper.readValue(s, WalletAsset[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String cash = new String();
        Path path2 = Path.of("src","main","resources", "cash.txt");
        String s2 = null;
        try {
            s2 = Files.readString(path2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            cash = objectMapper.readValue(s2, String.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Wallet wallet = new Wallet();

        for (WalletAsset w : wa) {
            wallet.addAsset(w);
        }
        wallet.addCash(cash);


    return wallet;

    }
}

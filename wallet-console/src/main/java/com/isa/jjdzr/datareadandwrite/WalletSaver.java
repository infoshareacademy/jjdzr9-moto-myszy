package com.isa.jjdzr.datareadandwrite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.jjdzr.dto.Wallet;
import com.isa.jjdzr.dto.WalletAsset;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class WalletSaver {

    public void saveWallet(Wallet wallet)  {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        WalletAsset[] wa = new WalletAsset[wallet.getWallet().size()];
        for (int i = 0; i < wallet.getWallet().size(); i++) {
            wa[i] = wallet.getWallet().get(i);
        }
        try {
            s = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(wa);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Path path = Path.of("src", "main", "resources", "walletassets.txt");
        try {
            Files.writeString(path, s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            s = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(wallet.getCash());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Path pathb = Path.of("src", "main", "resources", "cash.txt");
        try {
            Files.writeString(pathb, s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

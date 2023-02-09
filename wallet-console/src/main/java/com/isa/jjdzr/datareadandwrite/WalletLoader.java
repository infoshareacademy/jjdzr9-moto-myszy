package com.isa.jjdzr.datareadandwrite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.jjdzr.dto.Wallet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WalletLoader {
    public Wallet loadWallet(){

        ObjectMapper objectMapper = new ObjectMapper();
        Wallet wallet = new Wallet();

        Path path = Path.of("src","main","resources","wallet.txt");
        String s = null;
        try {
            s = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(s);
        try {
            wallet = objectMapper.readValue(s, Wallet.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    return wallet;

    }
}

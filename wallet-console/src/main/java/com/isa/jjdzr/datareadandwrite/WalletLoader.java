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
        Wallet wallet;

        Path path = Path.of("classes","wallet.txt");
        String s = null;
        try {
            s = Files.readString(path);
        } catch (IOException e) {
            System.err.println("Brak portfela do wczytania. Najpierw zapisz jakiś.");
            System.err.println("Powrót do menu głównego.");
            return null;
        }
        try {
            wallet = objectMapper.readValue(s, Wallet.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    return wallet;

    }
}

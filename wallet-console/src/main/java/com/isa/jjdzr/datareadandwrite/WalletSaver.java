package com.isa.jjdzr.datareadandwrite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.jjdzr.dto.Wallet;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class WalletSaver {

    public void saveWallet(Wallet wallet)  {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(wallet);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Path path = Path.of("classes","wallet.txt");
        try {
            Files.writeString(path, s);
        } catch (IOException e) {
            File file = new File(path.toUri());
            try {
                file.createNewFile();
                Files.writeString(path, s);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

}

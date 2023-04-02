package com.isa.jjdzr.walletcore.market;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.jjdzr.walletcore.dto.Asset;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.nio.file.Files.readString;

final class FileAssetRepository implements AssetRepository {
    @Override
    public List<Asset> retrieveAssets() {
        ObjectMapper objectMapper = new ObjectMapper();
        Asset[] retrievedAssets;
        String[] filenames = new String[]{"market1.txt", "market2.txt", "market3.txt", "market4.txt", "market5.txt", "market6.txt"};
        String randomfile = filenames[new Random().nextInt(filenames.length)];
        Path path = Path.of("data", "market", randomfile);
        String s = null;
        try {
            s = readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            retrievedAssets = objectMapper.readValue(s, Asset[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<Asset> retrievedAssetsList = new ArrayList<>(Arrays.asList(retrievedAssets));

        for (Asset asset : retrievedAssetsList) {
            asset.setName(asset.getName());
        }

        return retrievedAssetsList;
    }

}

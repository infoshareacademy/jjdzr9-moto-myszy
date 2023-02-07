package com.isa.jjdzr.market;

import com.isa.jjdzr.dto.Asset;

import java.util.List;

public class Market {

    private final AssetRepository repository;

    public Market() {
        this.repository = new FileAssetRepository();
    }

    public List<Asset> availableAssets(){
        return repository.retrieveAssets();
    }





}

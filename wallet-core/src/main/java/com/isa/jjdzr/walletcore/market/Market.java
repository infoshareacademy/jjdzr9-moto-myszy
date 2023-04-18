package com.isa.jjdzr.walletcore.market;

import com.isa.jjdzr.walletcore.dto.Asset;

import java.util.List;

public class Market {

    private final AssetRepository repository;

    public Market() {
        this.repository = new ApiAssetRepository();
    }

    public List<Asset> availableAssets() {
        return repository.retrieveAssets();
    }


}

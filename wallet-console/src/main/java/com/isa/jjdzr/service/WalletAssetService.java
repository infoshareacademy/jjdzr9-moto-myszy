package com.isa.jjdzr.service;

import com.isa.jjdzr.dto.Asset;
import com.isa.jjdzr.dto.WalletAsset;
import com.isa.jjdzr.market.Market;
import com.isa.jjdzr.repositories.WalletAssetRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class WalletAssetService {

    private final WalletAssetRepository walletAssetRepository;
    private final Market market;

    public WalletAssetService() {
        this.walletAssetRepository = new WalletAssetRepository();
        this.market = new Market();
    }

    public WalletAsset save(WalletAsset walletAsset) {
        return walletAssetRepository.save(walletAsset);
    }

    public List<WalletAsset> findWalletAssetsByWalletId(Long walletId) {
        return walletAssetRepository.getAll()
                .stream()
                .filter(wa -> walletId.equals(wa.getWalletId()))
                .toList();
    }
//TODO: need to do sth with this method
    public WalletAsset findCurrentPrice(Long waIndex) {
        WalletAsset walletAsset = walletAssetRepository.find(waIndex);
        String assetId = walletAsset.getAssetId();
        List<Asset> availableAssets = market.availableAssets();
        Asset asset = availableAssets.stream()
                .filter(a -> assetId.equals(a.getId()))
                .findFirst()
                .orElse(new Asset("wrong id", new BigDecimal(0)));
        walletAsset.setCurrentPrice(asset.getCurrentPrice());
        walletAssetRepository.save(walletAsset);
        return walletAsset;
    }

    public WalletAsset find(Long walletAssetId) {
        return walletAssetRepository.find(walletAssetId);
    }

    public void sellWalletAsset(Long walletAssetId, BigDecimal quantity) {
        WalletAsset walletAsset = walletAssetRepository.find(walletAssetId);
        if (Objects.equals(walletAsset.getQuantity(), quantity)) {
            walletAssetRepository.delete(walletAssetId);
        } else {
            reduceAssetQuantity(walletAssetId, quantity);
        }
    }

    private void reduceAssetQuantity(Long walletAssetId, BigDecimal quantity) {
        WalletAsset walletAsset = walletAssetRepository.find(walletAssetId);
        BigDecimal newQuantity = walletAsset.getQuantity().subtract(quantity);
        walletAsset.setQuantity(newQuantity);
        walletAssetRepository.save(walletAsset);
    }
}

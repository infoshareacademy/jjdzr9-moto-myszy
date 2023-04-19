package com.isa.jjdzr.walletcore.service;

import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletcore.market.Market;
import com.isa.jjdzr.walletcore.repositories.WalletAssetRepository;
import com.isa.jjdzr.walletcore.repositories.WalletAssetRepositoryImpl;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
@RequiredArgsConstructor
public class WalletAssetServiceImpl implements WalletAssetService{

    private final WalletAssetRepository walletAssetRepository;
    private final Market market;

    public WalletAssetServiceImpl() {
        this.walletAssetRepository = new WalletAssetRepositoryImpl();
        this.market = new Market();
    }


    @Override
    public WalletAsset save(WalletAsset walletAsset) {
        return walletAssetRepository.save(walletAsset);
    }

    @Override
    public List<WalletAsset> findWalletAssetsByWalletId(Long walletId) {
        return walletAssetRepository.getAll()
                .stream()
                .filter(wa -> walletId.equals(wa.getWalletId()))
                .toList();
    }

    //TODO: need to do sth with this method
    @Override
    public WalletAsset findCurrentPrice(Long waIndex) {
        WalletAsset walletAsset = walletAssetRepository.find(waIndex);
        String assetId = walletAsset.getAssetId();
        List<Asset> availableAssets = market.availableAssets();
        Asset asset = availableAssets.stream()
                .filter(a -> assetId.equals(a.getId()))
                .findFirst()
                .orElse(new Asset("wrong id","wrong name" ,new BigDecimal(0)));
        walletAsset.setCurrentPrice(asset.getCurrentPrice());
        walletAssetRepository.save(walletAsset);
        return walletAsset;
    }

    @Override
    public WalletAsset find(Long walletAssetId) {
        return walletAssetRepository.find(walletAssetId);
    }
    @Override
    public Long sellWalletAsset(Long walletAssetId, BigDecimal quantity) {
        WalletAsset walletAsset = walletAssetRepository.find(walletAssetId);
        if (Objects.equals(walletAsset.getQuantity(), quantity)) {
            walletAssetRepository.delete(walletAssetId);
        } else {
            reduceAssetQuantity(walletAssetId, quantity);
        }
        return walletAssetId;
    }

    private void reduceAssetQuantity(Long walletAssetId, BigDecimal quantity) {
        WalletAsset walletAsset = walletAssetRepository.find(walletAssetId);
        BigDecimal newQuantity = walletAsset.getQuantity().subtract(quantity);
        walletAsset.setQuantity(newQuantity);
        walletAssetRepository.save(walletAsset);
    }
}

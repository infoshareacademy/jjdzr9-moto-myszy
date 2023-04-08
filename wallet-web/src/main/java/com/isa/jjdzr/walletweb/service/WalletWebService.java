package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletweb.dto.*;

import java.util.List;

public interface WalletWebService {
    Wallet find(Long walletId);
    List<WalletAsset> findWalletAssetsByWalletId(Long walletId);
    List<Wallet> getUserWallets(Long userId);
    Wallet saveWallet(Wallet wallet);
    void topUpWallet(Long walletId, TopUpDto topUpDto);
    List<DetailedWalletAssetDto> prepareDetailedWalletAssetDtos(Long walletId);
    WalletAsset findWalletAsset(Long walletAssetId);
    String checkPossibilityToSell(SellInfoDto sellInfo);
    Long sell(SellInfoDto sellInfo);
    String checkPossibilityToBuy(BuyInfoDto buyInfo);
    Long handleBuy(BuyInfoDto buyInfo);
    List<Asset> findMatchingAssets(FilterInputDto filterInput);
    void createWalletChart(List<DetailedWalletAssetDto> walletAssets, Wallet wallet);






}

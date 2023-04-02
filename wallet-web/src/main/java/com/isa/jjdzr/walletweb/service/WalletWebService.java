package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletcore.service.WalletAssetService;
import com.isa.jjdzr.walletcore.service.WalletService;
import com.isa.jjdzr.walletweb.Constants;
import com.isa.jjdzr.walletweb.dto.DetailedWalletAssetDto;
import com.isa.jjdzr.walletweb.dto.SellDto;
import com.isa.jjdzr.walletweb.dto.TopUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletWebService {
    private final WalletService walletServiceImpl;
    private final WalletAssetService walletAssetServiceImpl;

    public Wallet find(Long walletId) {
        return walletServiceImpl.find(walletId);
    }

    public List<WalletAsset> findWalletAssetsByWalletId(Long walletId) {
        return walletAssetServiceImpl.findWalletAssetsByWalletId(walletId);
    }

    public WalletAsset findCurrentPrice(Long id) {
        return walletAssetServiceImpl.findCurrentPrice(id);
    }

    public List<Wallet> getUserWallets(Long userId) {
        return walletServiceImpl.getUserWallets(userId);
    }

    public Wallet saveWallet(Wallet wallet) {
        return walletServiceImpl.saveWallet(wallet);
    }

    public void topUpWallet(Long walletId, TopUpDto topUpDto) {
        walletServiceImpl.topUpWallet(walletId, topUpDto.getCash());
    }

    public List<DetailedWalletAssetDto> prepareDetailedWalletAssetDtos(Long walletId) {
        List<WalletAsset> walletAssets = findWalletAssetsByWalletId(walletId);
        walletAssets = walletAssets.stream()
                .map(wa -> findCurrentPrice(wa.getId()))
                .toList();
        List<DetailedWalletAssetDto> result = new ArrayList<>();
        for (WalletAsset wa : walletAssets) {
            DetailedWalletAssetDto dto = createDetailedWalletAssetDto(wa);
            result.add(dto);
        }
        return result;
    }

    private DetailedWalletAssetDto createDetailedWalletAssetDto(WalletAsset wa) {
        DetailedWalletAssetDto result = new DetailedWalletAssetDto();
        //TODO: remake in constructor
        result.setId(wa.getId());
        result.setWalletId(wa.getWalletId());
        result.setAssetId(wa.getAssetId());
        result.setPurchasePrice(wa.getPurchasePrice());
        result.setCurrentPrice(wa.getCurrentPrice());
        result.setQuantity(wa.getQuantity());
        result.setPurchaseValue(result.getPurchasePrice().multiply(result.getQuantity()));
        result.setCurrentValue(result.getCurrentPrice().multiply(result.getQuantity()));
        BigDecimal profit = new BigDecimal(1).subtract(result.getPurchasePrice().divide(result.getCurrentPrice(), 4, RoundingMode.CEILING));
        result.setProfit(profit);
        return result;
    }

    public WalletAsset findWalletAsset(Long walletAssetId) {
        return walletAssetServiceImpl.find(walletAssetId);
    }

    public String checkPossibilityToSell(SellDto sellInfo) {
        WalletAsset walletAsset = walletAssetServiceImpl.find(sellInfo.getWalletAssetId());
        if (sellInfo.getQuantityToSell().compareTo(walletAsset.getQuantity()) > 0) {
            return Constants.NOT_SUFFICIENT_QUANTITY_IN_WALLET;
        } else {
            return Constants.SELL_POSSIBLE;
        }
    }

    public Long sell(SellDto sellInfo) {
        WalletAsset walletAsset = walletAssetServiceImpl.find(sellInfo.getWalletAssetId());
        walletServiceImpl.addCashFromTransaction(walletAsset.getWalletId(), sellInfo.getQuantityToSell(), walletAsset.getCurrentPrice());
        return walletAssetServiceImpl.sellWalletAsset(sellInfo.getWalletAssetId(), sellInfo.getQuantityToSell());
    }

        public List<Asset> findMatchingAssets(FilterInputDto filterInput) {
        List<Asset> availableAssets = market.availableAssets();
        List<Asset> result = new ArrayList<>();
        for (Asset asset : availableAssets) {
            if (asset.getId() != null && asset.getId().contains(filterInput.getFilterInput())) {
                result.add(asset);
            }
        }
        return result;
    }



    public Long sell(Long waId, BigDecimal quantity) {
        WalletAsset walletAsset = walletAssetServiceImpl.find(waId);
        walletServiceImpl.addCashFromTransaction(walletAsset.getWalletId(), quantity, walletAsset.getCurrentPrice());
        return walletAssetServiceImpl.sellWalletAsset(waId, quantity);
    }

}
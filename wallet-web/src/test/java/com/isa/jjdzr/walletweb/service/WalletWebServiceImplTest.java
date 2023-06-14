package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletcore.common.Constants;
import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletcore.market.Market;
import com.isa.jjdzr.walletcore.service.WalletAssetService;
import com.isa.jjdzr.walletcore.service.WalletService;
import com.isa.jjdzr.walletweb.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class WalletWebServiceImplTest {
    private WalletService walletService;
    private WalletAssetService walletAssetService;
    private Market market;
    private ChartsService chartsService;
    private WalletWebServiceImpl walletWebService;

    @BeforeEach
    void setUp() {
        walletService = mock(WalletService.class);
        walletAssetService = mock(WalletAssetService.class);
        market = mock(Market.class);
        chartsService = mock(ChartsService.class);
        walletWebService = new WalletWebServiceImpl(walletService, walletAssetService, market, chartsService);
    }

    @Test
    void find_shouldReturnWallet() {
        // Given
        Long walletId = 1L;
        Wallet expectedWallet = new Wallet();
        when(walletService.find(walletId)).thenReturn(expectedWallet);

        // When
        Wallet result = walletWebService.find(walletId);

        // Then
        Assertions.assertEquals(expectedWallet, result);
        verify(walletService).find(walletId);
    }

    @Test
    void findWalletAssetsByWalletId_shouldReturnWalletAssets() {
        // Given
        Long walletId = 1L;
        List<WalletAsset> expectedWalletAssets = new ArrayList<>();
        when(walletAssetService.findWalletAssetsByWalletId(walletId)).thenReturn(expectedWalletAssets);

        // When
        List<WalletAsset> result = walletWebService.findWalletAssetsByWalletId(walletId);

        // Then
        Assertions.assertEquals(expectedWalletAssets, result);
        verify(walletAssetService).findWalletAssetsByWalletId(walletId);
    }

    @Test
    void findCurrentPrice_shouldReturnWalletAsset() {
        // Given
        Long id = 1L;
        WalletAsset expectedWalletAsset = new WalletAsset();
        when(walletAssetService.findCurrentPrice(id)).thenReturn(expectedWalletAsset);

        // When
        WalletAsset result = walletWebService.findCurrentPrice(id);

        // Then
        Assertions.assertEquals(expectedWalletAsset, result);
        verify(walletAssetService).findCurrentPrice(id);
    }

    @Test
    void getUserWallets_shouldReturnUserWallets() {
        // Given
        Long userId = 1L;
        List<Wallet> expectedUserWallets = new ArrayList<>();
        when(walletService.getUserWallets(userId)).thenReturn(expectedUserWallets);

        // When
        List<Wallet> result = walletWebService.getUserWallets(userId);

        // Then
        Assertions.assertEquals(expectedUserWallets, result);
        verify(walletService).getUserWallets(userId);
    }

    @Test
    void saveWallet_shouldReturnSavedWallet() {
        // Given
        Wallet wallet = new Wallet();
        Wallet expectedSavedWallet = new Wallet();
        when(walletService.saveWallet(wallet)).thenReturn(expectedSavedWallet);

        // When
        Wallet result = walletWebService.saveWallet(wallet);

        // Then
        Assertions.assertEquals(expectedSavedWallet, result);
        verify(walletService).saveWallet(wallet);
    }

    @Test
    void topUpWallet_shouldCallTopUpWalletInWalletService() {
        // Given
        Long walletId = 1L;
        TopUpDto topUpDto = new TopUpDto();

        // When
        walletWebService.topUpWallet(walletId, topUpDto);

        // Then
        verify(walletService).topUpWallet(walletId, topUpDto.getCash());
    }

    @Test
    void findWalletAsset_shouldReturnWalletAsset() {
        // Given
        Long walletAssetId = 1L;
        WalletAsset expectedWalletAsset = new WalletAsset();
        when(walletAssetService.find(walletAssetId)).thenReturn(expectedWalletAsset);

        // When
        WalletAsset result = walletWebService.findWalletAsset(walletAssetId);

        // Then
        Assertions.assertEquals(expectedWalletAsset, result);
        verify(walletAssetService).find(walletAssetId);
    }
    @Test
    void checkPossibilityToBuy_shouldReturnBuyPossibleWhenEnoughMoney() {
        // Given
        BuyInfoDto buyInfo = new BuyInfoDto();
        buyInfo.setWalletId(1L);
        buyInfo.setPrice(BigDecimal.valueOf(100));
        buyInfo.setQuantity(BigDecimal.valueOf(5));

        Wallet wallet = new Wallet();
        wallet.setCash(BigDecimal.valueOf(1000));

        when(walletService.find(buyInfo.getWalletId())).thenReturn(wallet);

        // When
        String result = walletWebService.checkPossibilityToBuy(buyInfo);

        // Then
        Assertions.assertEquals(Constants.BUY_POSSIBLE, result);
        verify(walletService).find(buyInfo.getWalletId());
    }

    @Test
    void checkPossibilityToSell_shouldReturnSellPossibleWhenQuantityToSellEqualsWalletAssetQuantity() {
        // Given
        SellInfoDto sellInfo = new SellInfoDto();
        sellInfo.setWalletAssetId(1L);
        sellInfo.setQuantityToSell(BigDecimal.valueOf(10));

        WalletAsset walletAsset = new WalletAsset();
        walletAsset.setWalletId(1L);
        walletAsset.setQuantity(BigDecimal.valueOf(10));

        when(walletAssetService.find(sellInfo.getWalletAssetId())).thenReturn(walletAsset);

        // When
        String result = walletWebService.checkPossibilityToSell(sellInfo);

        // Then
        Assertions.assertEquals(Constants.SELL_POSSIBLE, result);
        verify(walletAssetService).find(sellInfo.getWalletAssetId());
    }

    @Test
    void findMatchingAssets_shouldReturnMatchingAssets() {
        // Given
        FilterInputDto filterInput = new FilterInputDto();
        filterInput.setFilterInput("btc");

        Asset asset1 = new Asset();
        asset1.setId("BTC");
        asset1.setName("Bitcoin");
        Asset asset2 = new Asset();
        asset2.setId("ETH");
        asset2.setName("Ethereum");

        List<Asset> availableAssets = new ArrayList<>();
        availableAssets.add(asset1);
        availableAssets.add(asset2);

        when(market.availableAssets()).thenReturn(availableAssets);

        // When
        List<Asset> result = walletWebService.findMatchingAssets(filterInput);

        // Then
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(asset1, result.get(0));
        verify(market).availableAssets();
    }
    @Test
    void findMatchingAssets_shouldReturnEmptyListWhenNoMatchingAssets() {
        // Given
        FilterInputDto filterInput = new FilterInputDto();
        filterInput.setFilterInput("xyz");

        Asset asset1 = new Asset();
        asset1.setId("BTC");
        asset1.setName("Bitcoin");
        Asset asset2 = new Asset();
        asset2.setId("ETH");
        asset2.setName("Ethereum");

        List<Asset> availableAssets = new ArrayList<>();
        availableAssets.add(asset1);
        availableAssets.add(asset2);

        when(market.availableAssets()).thenReturn(availableAssets);

        // When
        List<Asset> result = walletWebService.findMatchingAssets(filterInput);

        // Then
        Assertions.assertEquals(0, result.size());
        verify(market).availableAssets();
    }

    @Test
    void createWalletChart_shouldCallChartsServiceWithCorrectArguments() {
        // Given
        List<DetailedWalletAssetDto> walletAssets = new ArrayList<>();
        Wallet wallet = new Wallet();

        // When
        walletWebService.createWalletChart(walletAssets, wallet);

        // Then
        verify(chartsService).createWalletChart(walletAssets, wallet);
    }

}

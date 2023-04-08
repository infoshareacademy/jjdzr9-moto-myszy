package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletweb.dto.DetailedWalletAssetDto;

import java.math.BigDecimal;
import java.util.List;

public interface ChartsService {
    void createWalletChart(List<DetailedWalletAssetDto> walletAssets, Wallet wallet);

}

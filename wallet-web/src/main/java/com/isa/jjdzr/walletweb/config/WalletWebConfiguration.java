package com.isa.jjdzr.walletweb.config;

import com.isa.jjdzr.walletcore.market.Market;
import com.isa.jjdzr.walletcore.repositories.WalletAssetRepository;
import com.isa.jjdzr.walletcore.repositories.WalletAssetRepositoryImpl;
import com.isa.jjdzr.walletcore.repositories.WalletRepository;
import com.isa.jjdzr.walletcore.repositories.WalletRepositoryImpl;
import com.isa.jjdzr.walletcore.service.WalletAssetService;
import com.isa.jjdzr.walletcore.service.WalletAssetServiceImpl;
import com.isa.jjdzr.walletcore.service.WalletService;
import com.isa.jjdzr.walletcore.service.WalletServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletWebConfiguration {

    @Bean
    public Market market() {
        return new Market();
    }

    @Bean
    public WalletService walletService(WalletRepository walletRepository, WalletAssetService walletAssetService) {
        return new WalletServiceImpl(walletRepository, walletAssetService);
    }

    @Bean
    public WalletAssetService walletAssetService(WalletAssetRepository walletAssetRepository, Market market) {
        return new WalletAssetServiceImpl(walletAssetRepository, market);
    }

    @Bean
    public WalletRepository walletRepository() {
        return new WalletRepositoryImpl();
    }

    @Bean
    public WalletAssetRepository walletAssetRepository() {
        return new WalletAssetRepositoryImpl();
    }
}

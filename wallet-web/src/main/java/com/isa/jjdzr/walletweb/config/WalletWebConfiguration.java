package com.isa.jjdzr.walletweb.config;

import com.isa.jjdzr.walletcore.market.Market;
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
    public WalletService walletService() {
        return new WalletServiceImpl();
    }

    @Bean
    public WalletAssetService walletAssetService() {
        return new WalletAssetServiceImpl();
    }
}

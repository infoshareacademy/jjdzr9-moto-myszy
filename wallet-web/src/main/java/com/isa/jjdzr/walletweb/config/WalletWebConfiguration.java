package com.isa.jjdzr.walletweb.config;

import com.isa.jjdzr.walletcore.market.Market;
import com.isa.jjdzr.walletcore.service.WalletAssetService;
import com.isa.jjdzr.walletcore.service.WalletService;
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
        return new WalletService();
    }

    @Bean
    public WalletAssetService walletAssetService() {
        return new WalletAssetService();
    }
}

package com.isa.jjdzr.walletweb.config;

import com.isa.jjdzr.walletcore.market.Market;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletWebConfiguration {

    @Bean
    public Market market() {
        return new Market();
    }
}

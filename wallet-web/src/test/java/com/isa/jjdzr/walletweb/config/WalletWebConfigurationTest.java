package com.isa.jjdzr.walletweb.config;

import com.isa.jjdzr.walletcore.market.HistoricalMarket;
import com.isa.jjdzr.walletcore.market.Market;
import com.isa.jjdzr.walletcore.repositories.WalletAssetRepository;
import com.isa.jjdzr.walletcore.repositories.WalletRepository;
import com.isa.jjdzr.walletcore.service.WalletAssetService;
import io.minio.MinioClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class WalletWebConfigurationTest {

    @InjectMocks
    private WalletWebConfiguration walletWebConfiguration;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private WalletAssetService walletAssetService;

    @Mock
    private WalletAssetRepository walletAssetRepository;

    @Mock
    private Market market;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testWalletRepository() {
        WalletRepository result = walletWebConfiguration.walletRepository();
        assertNotNull(result);
    }

    @Test
    void testWalletAssetRepository() {
        WalletAssetRepository result = walletWebConfiguration.walletAssetRepository();
        assertNotNull(result);
    }

    @Test
    void testHistoricalMarket() {
        HistoricalMarket result = walletWebConfiguration.historicalMarket();
        assertNotNull(result);
    }

    @Test
    void testMinioClient() {
        MinioClient result = walletWebConfiguration.minioClient();
        assertNotNull(result);
    }
}
package com.isa.jjdzr.walletweb.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

class NbpApiServiceImplTest {
    @Test
    void testGetExchangeRates() {
        // Given
        NbpApiService nbpApiService = new NbpApiServiceImpl();

        // When
        List<Map<String, Object>> exchangeRates = nbpApiService.getExchangeRates();

        // Then
        Assertions.assertNotNull(exchangeRates);
        Assertions.assertFalse(exchangeRates.isEmpty());

        for (Map<String, Object> rateMap : exchangeRates) {
            Assertions.assertTrue(rateMap.containsKey("currency"));
            Assertions.assertTrue(rateMap.containsKey("code"));
            Assertions.assertTrue(rateMap.containsKey("mid"));
        }
    }
}

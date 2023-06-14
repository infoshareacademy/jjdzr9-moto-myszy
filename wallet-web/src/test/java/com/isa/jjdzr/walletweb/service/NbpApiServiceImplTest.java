package com.isa.jjdzr.walletweb.service;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

public class NbpApiServiceImplTest {
    @Test
    public void testGetExchangeRates() {
        // Given
        NbpApiService nbpApiService = new NbpApiServiceImpl();

        // When
        List<Map<String, Object>> exchangeRates = nbpApiService.getExchangeRates();

        // Then
        assertNotNull(exchangeRates);
        assertFalse(exchangeRates.isEmpty());

        for (Map<String, Object> rateMap : exchangeRates) {
            assertTrue(rateMap.containsKey("currency"));
            assertTrue(rateMap.containsKey("code"));
            assertTrue(rateMap.containsKey("mid"));
        }
    }
}

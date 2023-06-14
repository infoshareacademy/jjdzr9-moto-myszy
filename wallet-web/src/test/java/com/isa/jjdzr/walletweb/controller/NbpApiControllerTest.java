package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletcore.api.ApiNbp;
import com.isa.jjdzr.walletweb.service.NbpApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

class NbpApiControllerTest {
    NbpApiService apiNbpService = Mockito.mock(NbpApiService.class);
    NbpApiController nbpApiController = new NbpApiController(apiNbpService);

    @Test
    void getExchangeRatesTest() {
        Model model = Mockito.mock(Model.class);
        Map<String, Object> rateMap = new HashMap<>();
        when(apiNbpService.getExchangeRates()).thenReturn(List.of(rateMap));
        String result = nbpApiController.getExchangeRates(model);
        Assertions.assertEquals("exchange-rates", result);
    }

    @Test
    void testPrintExchangeRates() {
        ApiNbp apiNbp = new ApiNbp();

        assertDoesNotThrow(apiNbp::printExchangeRates);
    }
}

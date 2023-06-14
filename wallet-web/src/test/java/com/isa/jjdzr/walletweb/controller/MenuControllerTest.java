package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.dto.HistoricalDataDto;
import com.isa.jjdzr.walletcore.market.HistoricalMarket;
import com.isa.jjdzr.walletcore.market.Market;
import com.isa.jjdzr.walletweb.dto.FilterInputDto;
import com.isa.jjdzr.walletweb.dto.User;
import com.isa.jjdzr.walletweb.service.ChartsService;
import com.isa.jjdzr.walletweb.service.WalletWebService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MenuControllerTest {
    Market market = Mockito.mock(Market.class);
    WalletWebService walletWebServiceImpl = Mockito.mock(WalletWebService.class);
    HistoricalMarket historicalMarket = Mockito.mock(HistoricalMarket.class);
    ChartsService chartsService = Mockito.mock(ChartsService.class);
    MenuController menuController = new MenuController(market, walletWebServiceImpl, historicalMarket, chartsService);

    @Test
    public void getHomepageTest() {
        Model model = Mockito.mock(Model.class);
        String result = menuController.getHomepage(model);
        assertEquals("index", result);
    }

    @Test
    public void getLoginTest() {
        Model model = Mockito.mock(Model.class);
        String result = menuController.getLogin(model);
        assertEquals("log-in", result);
    }

    @Test
    public void logoutTest() {
        HttpSession session = Mockito.mock(HttpSession.class);
        RedirectAttributes redirectAttributes = Mockito.mock(RedirectAttributes.class);
        String result = menuController.logout(session, redirectAttributes);
        assertEquals("redirect:/login", result);
    }

    @Test
    public void getMarketTest() {
        Model model = Mockito.mock(Model.class);
        String result = menuController.getMarket(model);
        assertEquals("market", result);
    }

    @Test
    public void searchTest() {
        FilterInputDto filterInput = Mockito.mock(FilterInputDto.class);
        Model model = Mockito.mock(Model.class);
        Asset asset = new Asset();
        when(walletWebServiceImpl.findMatchingAssets(filterInput)).thenReturn(List.of(asset));
        String result = menuController.search(filterInput, model);
        assertEquals("market", result);
    }

    @Test
    public void getHistoricalDataTest() {
        String id = "testId";
        Model model = Mockito.mock(Model.class);
        HistoricalDataDto historicalDataDto = new HistoricalDataDto();
        when(historicalMarket.getMonthlyData(id)).thenReturn(List.of(historicalDataDto));
        String result = menuController.getHistoricalData(id, model);
        assertEquals("historical-data", result);
    }
}

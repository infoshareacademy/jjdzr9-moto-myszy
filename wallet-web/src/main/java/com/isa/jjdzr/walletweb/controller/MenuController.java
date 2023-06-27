package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.dto.HistoricalDataDto;
import com.isa.jjdzr.walletcore.market.HistoricalMarket;
import com.isa.jjdzr.walletcore.market.Market;
import static com.isa.jjdzr.walletcore.common.Constants.*;
import com.isa.jjdzr.walletweb.dto.FilterInputDto;
import com.isa.jjdzr.walletweb.dto.UserDto;
import com.isa.jjdzr.walletweb.service.ChartsService;
import com.isa.jjdzr.walletweb.service.WalletWebService;
import static com.isa.jjdzr.walletweb.webcommons.WebConstants.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {
    private final Market market;
    private final WalletWebService walletWebServiceImpl;
    private final HistoricalMarket historicalMarket;
    private final ChartsService chartsService;

    @GetMapping("/")
    public String getHomepage(Model model) {
        return INDEX;
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute(MODEL_USER_DTO, new UserDto());
        return LOG_IN;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        String status = LOGOUT_SUCCESSFUL;
        redirectAttributes.addFlashAttribute(STATUS, status);
        return REDIRECT_LOG_IN;
    }

    @GetMapping("/market")
    public String getMarket(Model model) {
        model.addAttribute(MARKET, market.availableAssets());
        model.addAttribute(FILTER_INPUT, new FilterInputDto());
        return MARKET;
    }

    @GetMapping("/market/search")
    public String search(FilterInputDto filterInput, Model model) {
        List<Asset> matchingAssets = walletWebServiceImpl.findMatchingAssets(filterInput);
        model.addAttribute(MARKET, market.availableAssets());
        model.addAttribute(FILTER_INPUT, new FilterInputDto());
        return MARKET;
    }

    @GetMapping("/market/history/{id}")
    public String getHistoricalData(@PathVariable("id") String id, Model model) {
        List<HistoricalDataDto> history = historicalMarket.getMonthlyData(id);
        chartsService.createCandleStickChart(history);
        model.addAttribute(HISTORY, history);
        return HISTORICAL_DATA;
    }

}


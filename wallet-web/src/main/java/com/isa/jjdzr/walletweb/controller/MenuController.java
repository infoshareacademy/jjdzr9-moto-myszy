package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.dto.HistoricalDataDto;
import com.isa.jjdzr.walletcore.market.HistoricalMarket;
import com.isa.jjdzr.walletcore.market.Market;
import com.isa.jjdzr.walletcore.common.Constants;
import com.isa.jjdzr.walletweb.dto.FilterInputDto;
import com.isa.jjdzr.walletweb.dto.UserDto;
import com.isa.jjdzr.walletweb.service.ChartsService;
import com.isa.jjdzr.walletweb.service.WalletWebService;
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
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("user", new UserDto());
        return "log-in";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        String status = Constants.LOGOUT_SUCCESSFUL;
        redirectAttributes.addFlashAttribute(Constants.STATUS, status);
        return "redirect:/login";
    }

    @GetMapping("/market")
    public String getMarket(Model model) {
        model.addAttribute("market", market.availableAssets());
        model.addAttribute("filterInput", new FilterInputDto());
        return "market";
    }

    @GetMapping("/market/search")
    public String search(FilterInputDto filterInput, Model model) {
        List<Asset> matchingAssets = walletWebServiceImpl.findMatchingAssets(filterInput);

        model.addAttribute("market", matchingAssets);
        model.addAttribute("filterInput", new FilterInputDto());
        return "market";
    }

    @GetMapping("/market/history/{id}")
    public String getHistoricalData(@PathVariable("id") String id, Model model) {
        List<HistoricalDataDto> history = historicalMarket.getMonthlyData(id);
        chartsService.createCandleStickChart(history);
        model.addAttribute("history", history);
        return "historical-data";
    }

}


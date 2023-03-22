package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletcore.market.Market;
import com.isa.jjdzr.walletweb.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MenuController {
    private final Market market;


    @GetMapping("/")
    public String getHomepage(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("user", new User());
        return "log-in";
    }

    @GetMapping("/wallet-view")
    public String getWalletView(Model model) {
        return "wallet-view";
    }

    @GetMapping("/market")
    public String getMarket(Model model) {
        model.addAttribute("market", market.availableAssets());
        return "market";
    }


}


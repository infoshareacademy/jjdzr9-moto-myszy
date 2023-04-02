package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.market.Market;
import com.isa.jjdzr.walletweb.Constants;
import com.isa.jjdzr.walletweb.dto.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        String status = Constants.LOGOUT_SUCCESSFUL;
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/login";
    }

//    @GetMapping("/wallet-view")
//    public String getWalletView(Model model) {
//        return "wallet-view";
//    }

    @GetMapping("/market")
    public String getMarket(Model model) {
        model.addAttribute("market", market.availableAssets());
        return "market";
    }

    @GetMapping("/market/search")
    public String search(Model model, @RequestParam(name = "name", required = false) String name) {
        List<Asset> availableAssets = market.availableAssets();
        List<Asset> matchingAssets = new ArrayList<>();
        for (Asset asset : availableAssets) {
            if (asset.getName() != null && asset.getName().contains(name)) {
                matchingAssets.add(asset);
            }
        }
        model.addAttribute("assets", matchingAssets);
        return "market";
    }


}


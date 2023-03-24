package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletcore.service.WalletAssetService;
import com.isa.jjdzr.walletcore.service.WalletService;
import com.isa.jjdzr.walletweb.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    private final WalletAssetService walletAssetService;

    @GetMapping("/create-wallet")
    public String createWallet(Model model) {
        model.addAttribute("wallet", new Wallet());
        return "create-wallet";
    }

    @PostMapping("/handle-wallet-creation")
    public String createWallet(@Valid Wallet wallet, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) return "create-wallet";
        //TODO: add this in thymeleaf (as logged user id)
        wallet.setUserId(0L);
        walletService.saveWallet(wallet);
        String status = Constants.SUCCESS_STATUS;
        redirectAttributes.addFlashAttribute("status", status);
        //TODO: adding newly created wallet to session
        return "redirect:/wallet-view";
    }

    @GetMapping("/wallet-view")
    public String showWallet(Model model){
        List<WalletAsset> walletAssets = walletAssetService.findWalletAssetsByWalletId(0L);
        Wallet wallet = walletService.find(0L);
        model.addAttribute("walletAssets", walletAssets);
        model.addAttribute("wallet", wallet);
        return "wallet-view";
    }
}

package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletcore.service.WalletAssetService;
import com.isa.jjdzr.walletcore.service.WalletService;
import com.isa.jjdzr.walletweb.Constants;
import com.isa.jjdzr.walletweb.dto.TopUpDto;
import com.isa.jjdzr.walletweb.service.WalletWebService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WalletController {

    private final WalletWebService walletWebService;

    @GetMapping("/create-wallet")
    public String createWallet(Model model) {
        model.addAttribute("wallet", new Wallet());
        return "create-wallet";
    }

    @GetMapping("/wallet-view/{walletId}")
    public String showWallet(@PathVariable("walletId") Long walletId, Model model) {
        if (walletId == -1L) return "redirect:/create-wallet";
        Wallet wallet = walletWebService.find(walletId);
        //TODO: put in one method
        List<WalletAsset> walletAssets = walletWebService.findWalletAssetsByWalletId(walletId);
        walletAssets = walletAssets.stream()
                .map(wa -> walletWebService.findCurrentPrice(wa.getId()))
                .toList();
        model.addAttribute("walletAssets", walletAssets);
        model.addAttribute("cash", wallet.getCash());
        return "wallet-view";
    }

    @GetMapping("/load-wallet/{userId}")
    public String loadWallet(Model model, @PathVariable("userId") Long userId) {
        if (userId == -1L) return "redirect:/login";
        List<Wallet> walletList = walletWebService.getUserWallets(userId);
        model.addAttribute(walletList);
        return "load-wallet";
    }

    @PostMapping("/handle-wallet-creation/{userId}")
    public String createWallet(@Valid Wallet wallet, @PathVariable("userId") Long userId, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session) {
        if (result.hasErrors()) return "create-wallet";
        wallet.setUserId(userId);
        walletWebService.saveWallet(wallet);
        String status = Constants.SUCCESS_STATUS;
        redirectAttributes.addFlashAttribute("status", status);
        session.setAttribute("wallet", wallet);
        return "redirect:/wallet-view/" + wallet.getId();
    }

    @GetMapping("/handleLoad/{walletId}")
    public String handleWalletLoading(@PathVariable("walletId") Long walletId, HttpSession session) {
        Wallet wallet = walletWebService.find(walletId);
        session.setAttribute("wallet", wallet.getId());
        return "redirect:/wallet-view/{walletId}";
    }

    @GetMapping("/top-up-wallet")
    public String topUpWallet(Model model) {
        model.addAttribute("topUpDto", new TopUpDto());
        return "top-up-wallet";
    }

    @PutMapping("/handleTopUp/{walletId}")
    public String handleTopUp(@PathVariable("walletId") Long walletId,@Valid TopUpDto topUpDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) return "top-up-wallet";
        String status = Constants.TOP_UP_SUCCESS;
        redirectAttributes.addFlashAttribute("status", status);
        walletWebService.topUpWallet(walletId, topUpDto);
        return "redirect:/wallet-view/" + walletId;
    }

}

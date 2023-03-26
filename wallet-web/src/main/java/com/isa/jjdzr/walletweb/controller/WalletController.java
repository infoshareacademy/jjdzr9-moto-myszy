package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletcore.service.WalletAssetService;
import com.isa.jjdzr.walletcore.service.WalletService;
import com.isa.jjdzr.walletweb.Constants;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletServiceImpl;
    private final WalletAssetService walletAssetServiceImpl;

    @GetMapping("/create-wallet")
    public String createWallet(Model model) {
        model.addAttribute("wallet", new Wallet());
        return "create-wallet";
    }

    @GetMapping("/wallet-view/{walletId}")
    public String showWallet(@PathVariable("walletId") Long walletId, Model model) {
        //TODO: redirect to wallet creation
        if (walletId == -1L) return "redirect:/";
        List<WalletAsset> walletAssets = walletAssetServiceImpl.findWalletAssetsByWalletId(walletId);
        model.addAttribute("walletAssets", walletAssets);
        return "wallet-view";
    }

    @GetMapping("/load-wallet/{userId}")
    public String loadWallet(Model model, @PathVariable("userId") Long userId) {
        if (userId == -1L) return "redirect:/login";
        List<Wallet> walletList = walletServiceImpl.getUserWallets(userId);
        model.addAttribute(walletList);
        return "load-wallet";
    }

    @PostMapping("/handle-wallet-creation/{userId}")
    public String createWallet(@Valid Wallet wallet, @PathVariable("userId") Long userId, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session) {
        if (result.hasErrors()) return "create-wallet";
        wallet.setUserId(userId);
        walletServiceImpl.saveWallet(wallet);
        String status = Constants.SUCCESS_STATUS;
        redirectAttributes.addFlashAttribute("status", status);
        session.setAttribute("wallet", wallet);
        return "redirect:/wallet-view/" + wallet.getId();
    }
}

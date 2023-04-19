package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletcore.common.Constants;
import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletweb.dto.DetailedWalletAssetDto;
import com.isa.jjdzr.walletweb.dto.TopUpDto;
import com.isa.jjdzr.walletweb.service.WalletWebService;
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

    private final WalletWebService walletWebServiceImpl;

    @GetMapping("/create-wallet")
    public String createWallet(Model model) {
        model.addAttribute("wallet", new Wallet());
        return "create-wallet";
    }

    @GetMapping("/wallet-view/{walletId}")
    public String showWallet(@PathVariable("walletId") Long walletId, Model model) {
        if (walletId == Constants.NOT_IN_SESSION) return "redirect:/create-wallet";
        Wallet wallet = walletWebServiceImpl.find(walletId);
        List<DetailedWalletAssetDto> walletAssets = walletWebServiceImpl.prepareDetailedWalletAssetDtos(walletId);
        walletWebServiceImpl.createWalletChart(walletAssets, wallet);
        model.addAttribute("walletAssets", walletAssets);
        model.addAttribute("cash", wallet.getCash());
        return "wallet-view";
    }

    @GetMapping("/load-wallet/{userId}")
    public String loadWallet(Model model, @PathVariable("userId") Long userId) {
        if (userId == Constants.NOT_IN_SESSION) return "redirect:/login";
        List<Wallet> walletList = walletWebServiceImpl.getUserWallets(userId);
        model.addAttribute(walletList);
        return "load-wallet";
    }

    @PostMapping("/handle-wallet-creation/{userId}")
    public String createWallet(@Valid Wallet wallet, BindingResult result, RedirectAttributes redirectAttributes,
                               HttpSession session, @PathVariable("userId") Long userId) {
        if (result.hasErrors()) {
            return "create-wallet";
        }
        wallet.setUserId(userId);
        walletWebServiceImpl.saveWallet(wallet);
        String status = Constants.SUCCESS_STATUS;
        redirectAttributes.addFlashAttribute(Constants.STATUS, status);
        session.setAttribute("wallet", wallet.getId());
        return "redirect:/wallet-view/" + wallet.getId();
    }

    @GetMapping("/handleLoad/{walletId}")
    public String handleWalletLoading(@PathVariable("walletId") Long walletId, HttpSession session) {
        Wallet wallet = walletWebServiceImpl.find(walletId);
        session.setAttribute("wallet", wallet.getId());
        return "redirect:/wallet-view/" + walletId;
    }

    @GetMapping("/top-up-wallet/{walletId}")
    public String topUpWallet(@PathVariable("walletId") Long walletId, Model model) {
        if (walletId == Constants.NOT_IN_SESSION) return "redirect:/create-wallet";
        model.addAttribute("topUpDto", new TopUpDto());
        return "top-up-wallet";
    }

    @PostMapping("/handleTopUp/{walletId}")
    public String handleTopUp(@PathVariable("walletId") Long walletId, @Valid TopUpDto topUpDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) return "top-up-wallet";
        String status = Constants.TOP_UP_SUCCESS;
        redirectAttributes.addFlashAttribute(Constants.STATUS, status);
        walletWebServiceImpl.topUpWallet(walletId, topUpDto);
        return "redirect:/wallet-view/" + walletId;
    }


}

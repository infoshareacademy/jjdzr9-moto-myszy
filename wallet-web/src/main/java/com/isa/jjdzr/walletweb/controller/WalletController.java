package com.isa.jjdzr.walletweb.controller;

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

import static com.isa.jjdzr.walletcore.common.Constants.*;
import static com.isa.jjdzr.walletweb.webcommons.WebConstants.*;

@Controller
@RequiredArgsConstructor
public class WalletController {

    private final WalletWebService walletWebServiceImpl;

    @GetMapping("/create-wallet")
    public String createWallet(Model model) {
        model.addAttribute(MODEL_WALLET, new Wallet());
        return CREATE_WALLET;
    }

    @GetMapping("/wallet-view/{walletId}")
    public String showWallet(@PathVariable("walletId") Long walletId, Model model) {
        if (walletId == NOT_IN_SESSION) return REDIRECT_CREATE_WALLET;
        Wallet wallet = walletWebServiceImpl.find(walletId);
        List<DetailedWalletAssetDto> walletAssets = walletWebServiceImpl.prepareDetailedWalletAssetDtos(walletId);
        walletWebServiceImpl.createWalletChart(walletAssets, wallet);
        model.addAttribute(MODEL_WALLET_ASSETS, walletAssets);
        model.addAttribute(MODEL_CASH, wallet.getCash());
        return WALLET_VIEW;
    }

    @GetMapping("/load-wallet/{userId}")
    public String loadWallet(Model model, @PathVariable("userId") Long userId) {
        if (userId == NOT_IN_SESSION) return REDIRECT_LOG_IN;
        List<Wallet> walletList = walletWebServiceImpl.getUserWallets(userId);
        model.addAttribute(walletList);
        return LOAD_WALLET;
    }

    @PostMapping("/handle-wallet-creation/{userId}")
    public String createWallet(@Valid Wallet wallet, BindingResult result, RedirectAttributes redirectAttributes,
                               HttpSession session, @PathVariable("userId") Long userId) {
        if (result.hasErrors()) {
            return CREATE_WALLET;
        }
        wallet.setUserId(userId);
        walletWebServiceImpl.saveWallet(wallet);
        String status = SUCCESS_STATUS;
        redirectAttributes.addFlashAttribute(STATUS, status);
        session.setAttribute(SESSION_WALLET, wallet.getId());
        return REDIRECT_WALLET_VIEW + wallet.getId();
    }

    @GetMapping("/handleLoad/{walletId}")
    public String handleWalletLoading(@PathVariable("walletId") Long walletId, HttpSession session) {
        Wallet wallet = walletWebServiceImpl.find(walletId);
        session.setAttribute(SESSION_WALLET, wallet.getId());
        return REDIRECT_WALLET_VIEW + walletId;
    }

    @GetMapping("/top-up-wallet/{walletId}")
    public String topUpWallet(@PathVariable("walletId") Long walletId, Model model) {
        if (walletId == NOT_IN_SESSION) return REDIRECT_CREATE_WALLET;
        model.addAttribute(MODEL_TOP_UP, new TopUpDto());
        return TOP_UP_WALLET;
    }

    @PostMapping("/handleTopUp/{walletId}")
    public String handleTopUp(@PathVariable("walletId") Long walletId, @Valid TopUpDto topUpDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) return TOP_UP_WALLET;
        String status = TOP_UP_SUCCESS;
        redirectAttributes.addFlashAttribute(STATUS, status);
        walletWebServiceImpl.topUpWallet(walletId, topUpDto);
        return REDIRECT_WALLET_VIEW + walletId;
    }


}

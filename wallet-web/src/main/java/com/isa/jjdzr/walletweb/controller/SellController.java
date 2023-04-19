package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletcore.common.Constants;
import com.isa.jjdzr.walletcore.dto.WalletAsset;
import com.isa.jjdzr.walletweb.dto.SellInfoDto;
import com.isa.jjdzr.walletweb.service.WalletWebService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class SellController {

    private final WalletWebService walletWebServiceImpl;

    @GetMapping("/sell-asset/{waId}")
    public String getSellWalletAsset(@PathVariable("waId") Long waId, Model model) {
        WalletAsset walletAsset = walletWebServiceImpl.findWalletAsset(waId);
        model.addAttribute("walletAsset", walletAsset);
        SellInfoDto sellInfo = new SellInfoDto();
        sellInfo.setWalletAssetId(waId);
        model.addAttribute("sellInfo", sellInfo);
        return "sell-asset";
    }

    @PostMapping("/handle-sell")
    public String sellWalletAsset(@Valid SellInfoDto sellInfo, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            String status = Constants.WRONG_INPUT;
            redirectAttributes.addFlashAttribute(Constants.STATUS, status);
            return "redirect:/sell-asset/" + sellInfo.getWalletAssetId();

        }
        String status = walletWebServiceImpl.checkPossibilityToSell(sellInfo);
        if (status.equals(Constants.NOT_SUFFICIENT_QUANTITY_IN_WALLET)) {
            result.rejectValue("quantityToSell", "", "Nie masz takiej ilości w portfelu");
            redirectAttributes.addFlashAttribute(Constants.STATUS, status);
            return "redirect:/sell-asset/" + sellInfo.getWalletAssetId();
        }
        Long walletId = walletWebServiceImpl.sell(sellInfo);
        redirectAttributes.addFlashAttribute(Constants.STATUS, status);
        return "redirect:/wallet-view/" + walletId;

    }
}

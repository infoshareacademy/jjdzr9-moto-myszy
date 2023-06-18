package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletcore.common.Constants;
import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.market.Market;
import com.isa.jjdzr.walletweb.dto.BuyInfoDto;
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

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class BuyController {

    private final WalletWebService walletWebServiceImpl;
    private final Market market;

    @GetMapping("/buy-asset/{id}/{walletId}")
    public String getBuyAsset(@PathVariable("id") String id,
                              @PathVariable("walletId") Long walletId, Model model) {
        if (walletId == Constants.NOT_IN_SESSION) return "redirect:/create-wallet";
        BuyInfoDto buyInfo = new BuyInfoDto();
        Asset asset = market.findById(id);
        if (Objects.equals(asset.getId(), "Złe id")) { return "market"; }
        buyInfo.setAssetId(id);
        buyInfo.setAssetName(asset.getName());
        buyInfo.setWalletId(walletId);
        buyInfo.setPrice(asset.getCurrentPrice());
        model.addAttribute("buyInfo", buyInfo);
        return "buy-asset";
    }

    @PostMapping("/handle-buy")
    public String buyWalletAsset(@Valid BuyInfoDto buyInfo, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            String status = Constants.WRONG_INPUT;
            redirectAttributes.addFlashAttribute(Constants.STATUS, status);
            return "redirect:/buy-asset/" + buyInfo.getAssetId() + "/" + buyInfo.getWalletId();
        }
        String status = walletWebServiceImpl.checkPossibilityToBuy(buyInfo);
        if (status.equals(Constants.NOT_ENOUGH_MONEY)) {
            redirectAttributes.addFlashAttribute(Constants.STATUS, status);
            return "redirect:/buy-asset/" + buyInfo.getAssetId() + "/" + buyInfo.getWalletId();
        }
        Long walletId = walletWebServiceImpl.handleBuy(buyInfo);
        redirectAttributes.addFlashAttribute(Constants.STATUS, status);
        return "redirect:/wallet-view/" + walletId;
    }
}

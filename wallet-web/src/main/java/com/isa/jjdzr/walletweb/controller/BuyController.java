package com.isa.jjdzr.walletweb.controller;

import static com.isa.jjdzr.walletcore.common.Constants.*;
import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.market.Market;
import com.isa.jjdzr.walletweb.dto.BuyInfoDto;
import com.isa.jjdzr.walletweb.service.WalletWebService;
import static com.isa.jjdzr.walletweb.webcommons.WebConstants.*;
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
        if (walletId == NOT_IN_SESSION) return REDIRECT_CREATE_WALLET;
        BuyInfoDto buyInfo = new BuyInfoDto();
        Asset asset = market.findById(id);
        if (Objects.equals(asset.getId(), WRONG_ID)) { return MARKET; }
        //TODO: remake it with builder/other class
        buyInfo.setAssetId(id);
        buyInfo.setAssetName(asset.getName());
        buyInfo.setWalletId(walletId);
        buyInfo.setPrice(asset.getCurrentPrice());
        model.addAttribute(MODEL_BUY_INFO, buyInfo);
        return BUY_ASSET;
    }

    @PostMapping("/handle-buy")
    public String buyWalletAsset(@Valid BuyInfoDto buyInfo, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            String status = WRONG_INPUT;
            redirectAttributes.addFlashAttribute(STATUS, status);
            return REDIRECT_BUY_ASSET + buyInfo.getAssetId() + "/" + buyInfo.getWalletId();
        }
        String status = walletWebServiceImpl.checkPossibilityToBuy(buyInfo);
        if (status.equals(NOT_ENOUGH_MONEY)) {
            redirectAttributes.addFlashAttribute(STATUS, status);
            return REDIRECT_BUY_ASSET + buyInfo.getAssetId() + "/" + buyInfo.getWalletId();
        }
        Long walletId = walletWebServiceImpl.handleBuy(buyInfo);
        redirectAttributes.addFlashAttribute(STATUS, status);
        return REDIRECT_WALLET_VIEW + walletId;
    }
}

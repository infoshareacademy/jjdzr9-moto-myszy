package com.isa.jjdzr.walletweb;

import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletcore.market.Market;
import com.isa.jjdzr.walletcore.service.WalletService;
import com.isa.jjdzr.walletweb.dto.User;
import com.isa.jjdzr.walletweb.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {
    private final Market market;
    private final UserService userService;
    private final WalletService walletService;

    @GetMapping("/")
    public String getHomepage(Model model){
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        return "log-in";
    }

    @GetMapping("/wallet-view")
    public String getWalletView(Model model){
        return "wallet-view";
    }

    @GetMapping("/market")
    public String getMarket(Model model){
        model.addAttribute("market",market.availableAssets());
        return "market";
    }

    @GetMapping("/register")
    public String getRegpage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/handleReg")
    public String register(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!user.getPassword().equals(user.getConfirmPassword())) result.rejectValue("password","",
                "Oba pola muszą być identyczne");
        if (userService.checkUserName(user)) result.rejectValue("username","","Nazwa użytkownika zajęta");
        if (result.hasErrors()) return "register";
        userService.addUser(user);
        String status = Constants.SUCCESS_STATUS;
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/login";
    }

    @GetMapping("/create-wallet")
    public String createWallet(Model model){
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
}


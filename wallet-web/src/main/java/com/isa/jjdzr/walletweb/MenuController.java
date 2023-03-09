package com.isa.jjdzr.walletweb;

import com.isa.jjdzr.walletweb.dto.User;
import com.isa.jjdzr.walletweb.userservice.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final UserService userService;
    private User currentUser;

    @GetMapping("/")
    public String getHomepage(Model model){
        menuService.addCurrentUser(model, currentUser);
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        menuService.addNewUser(model);
        menuService.addCurrentUser(model, currentUser);
        return "log-in";
    }

    @GetMapping("/market")
    public String getMarket(Model model){
        menuService.addCurrentUser(model, currentUser);
        return "market";
    }

    @GetMapping("/register")
    public String getRegpage(Model model){
        menuService.addNewUser(model);
        menuService.addCurrentUser(model, currentUser);
        return "register";
    }

    @PostMapping("/handleReg")
    public String register(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!user.getPassword().equals(user.getConfirmPassword())) result.rejectValue("password","",
                "Both fields must be the same");
        if (result.hasErrors()) return "register";
        userService.addUser(user);
        String status = Constants.SUCCESS_STATUS;
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/login";
    }

    @PostMapping("/handleLogin")
    public String login(User user){
        this.currentUser = userService.login(user);
        return "redirect:/";
    }
}

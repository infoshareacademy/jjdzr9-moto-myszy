package com.isa.jjdzr.walletweb;

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
    private final UserService userService;

    @GetMapping("/")
    public String getHomepage(Model model){
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        return "log-in";
    }

    @GetMapping("/market")
    public String getMarket(Model model){
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
                "Both fields must be the same");
        if (userService.checkUserName(user)) result.rejectValue("username","","Username already taken");
        if (result.hasErrors()) return "register";
        userService.addUser(user);
        String status = Constants.SUCCESS_STATUS;
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/login";
    }

}

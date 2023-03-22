package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletweb.Constants;
import com.isa.jjdzr.walletweb.dto.User;
import com.isa.jjdzr.walletweb.service.UserService;
import jakarta.servlet.http.HttpSession;
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
public class UserController {
    private final UserService userService;

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

    @PostMapping("/handleLogin")
    public String login(User user, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session) {
        Long userId = userService.login(user);
        if (userId == Constants.WRONG_USERNAME) result.rejectValue("username", "", "Użytkownik nie istnieje");
        if (userId == Constants.WRONG_PASSWORD) result.rejectValue("password", "", "Niepoprawne hasło");
        if (result.hasErrors()) return "login";
        User currentUser = userService.find(userId);
        String status = Constants.LOGIN_SUCCESSFUL;
        redirectAttributes.addFlashAttribute("status", status);
        session.setAttribute("user", currentUser);
        return "redirect:/";
    }
}

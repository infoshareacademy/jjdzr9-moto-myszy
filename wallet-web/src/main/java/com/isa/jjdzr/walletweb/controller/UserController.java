package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletcore.common.Constants;
import com.isa.jjdzr.walletweb.dto.UserDto;
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
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/handleReg")
    public String register(@Valid UserDto userDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            result.rejectValue("password", "",
                    "Oba pola muszą być identyczne");
        }
        if (userService.checkUserName(userDto)) {
            result.rejectValue("username", "", "Nazwa użytkownika zajęta");
        }
        if (result.hasErrors()) {
            return "register";
        }
        userService.addUser(userDto);
        String status = Constants.SUCCESS_STATUS;
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/login";
    }

    @PostMapping("/handleLogin")
    public String login(UserDto userDto, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session) {
        Long userId = userService.login(userDto);
        if (userId == Constants.WRONG_USERNAME || userId == Constants.WRONG_PASSWORD) {
            result.rejectValue("username", "", "Nieprawidłowe dane logowania");
        }
        if (result.hasErrors()) {
            return "log-in";
        }
        UserDto currentUserDto = userService.find(userId);
        String status = Constants.LOGIN_SUCCESSFUL;
        redirectAttributes.addFlashAttribute("status", status);
        session.setAttribute("user", currentUserDto);
        return "redirect:/";
    }
}

package com.isa.jjdzr.walletweb.controller;

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

import static com.isa.jjdzr.walletcore.common.Constants.*;
import static com.isa.jjdzr.walletweb.webcommons.WebConstants.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String getRegpage(Model model){
        model.addAttribute(MODEL_USER_DTO, new UserDto());
        return REGISTER;
    }

    @PostMapping("/handleReg")
    public String register(@Valid UserDto userDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            result.rejectValue(FIELD_PASSWORD, "",
                    ERROR_MSG_BOTH_FIELDS_MUST_BE_THE_SAME);
        }
        if (userService.checkUserName(userDto)) {
            result.rejectValue(FIELD_USERNAME, "", ERROR_MSG_USERNAME_TAKEN);
        }
        if (result.hasErrors()) {
            return REGISTER;
        }
        userService.addUser(userDto);
        String status = SUCCESS_STATUS;
        redirectAttributes.addFlashAttribute(STATUS, status);
        return REDIRECT_LOG_IN;
    }

    @PostMapping("/handleLogin")
    public String login(UserDto userDto, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session) {
        Long userId = userService.login(userDto);
        if (userId == WRONG_USERNAME || userId == WRONG_PASSWORD) {
            result.rejectValue(FIELD_USERNAME, "", ERROR_MSG_LOGIN);
        }
        if (result.hasErrors()) {
            return LOG_IN;
        }
        UserDto currentUserDto = userService.find(userId);
        String status = LOGIN_SUCCESSFUL;
        redirectAttributes.addFlashAttribute(STATUS, status);
        session.setAttribute(SESSION_USER, currentUserDto);
        return REDIRECT_INDEX;
    }
}

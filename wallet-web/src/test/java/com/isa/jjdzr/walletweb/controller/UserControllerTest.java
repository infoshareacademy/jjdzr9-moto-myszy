package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletweb.dto.UserDto;
import com.isa.jjdzr.walletweb.service.UserService;
import com.isa.jjdzr.walletcore.common.Constants;
import com.isa.jjdzr.walletweb.webcommons.WebConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    Model model;

    @Mock
    BindingResult result;

    @Mock
    RedirectAttributes redirectAttributes;

    @Mock
    HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRegpage() {
        String viewName = userController.getRegpage(model);
        assertEquals("register", viewName);
        verify(model, times(1)).addAttribute(eq("userDto"), any(UserDto.class));
    }

    @Test
    void testRegister() {
        UserDto user = new UserDto();
        user.setPassword("password");
        user.setConfirmPassword("password");
        when(userService.checkUserName(user)).thenReturn(false);
        String viewName = userController.register(user, result, redirectAttributes);
        assertEquals("redirect:/login", viewName);
        verify(userService, times(1)).addUser(user);
        verify(redirectAttributes, times(1)).addFlashAttribute("status", Constants.SUCCESS_STATUS);
    }

    @Test
    void testLogin() {
        UserDto user = new UserDto();
        when(userService.login(user)).thenReturn(1L);
        UserDto currentUser = new UserDto();
        when(userService.find(1L)).thenReturn(currentUser);
        String viewName = userController.login(user, result, redirectAttributes, session);
        assertEquals("redirect:/", viewName);
        verify(userService, times(1)).find(1L);
        verify(redirectAttributes, times(1)).addFlashAttribute("status", WebConstants.LOGIN_SUCCESSFUL);
        verify(session, times(1)).setAttribute("user", currentUser);
    }
}

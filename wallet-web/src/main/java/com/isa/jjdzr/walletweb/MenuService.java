package com.isa.jjdzr.walletweb;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class MenuService {
    public Model addCurrentUser(Model model, User user){
        if (user != null){
            return model.addAttribute("currentUser", user.getUsername());
        } else {
            return model.addAttribute("currentUser", "GUESS");
        }
    }

    public Model addNewUser(Model model){
        return model.addAttribute("user", new User());
    }
}

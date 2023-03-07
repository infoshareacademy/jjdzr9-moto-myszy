package com.isa.jjdzr.walletweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/")
    public String getHomepage(){
        return "index";
    }

    @GetMapping("/login")
    public String getLogIn(){
        return "log-in";
    }

    @GetMapping("/market")
    public String getMarket(){
        return "market";
    }

    @GetMapping("/register")
    public String getRegPage(){
        return "register";
    }
}

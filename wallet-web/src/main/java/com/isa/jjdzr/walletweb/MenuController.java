package com.isa.jjdzr.walletweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/")
    public String getHomepage(){
        return "index";
        }
}

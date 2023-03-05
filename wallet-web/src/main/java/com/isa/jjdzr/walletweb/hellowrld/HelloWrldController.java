package com.isa.jjdzr.walletweb.hellowrld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWrldController {

    @GetMapping()
    public String helloWrld(){
        return "Hello World!";
    }
}

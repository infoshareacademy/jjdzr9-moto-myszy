package com.isa.jjdzr.walletweb.hellowrld;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HTMLController {
    private final AssetService assetService;
    @GetMapping("/hello")
    public String helloWorld(Model model) {
        model.addAttribute("asset", assetService.returnFirstAsset());
        return "helloWorld";
    }
    @GetMapping("/list")
    public String controller2(Model model) {
        model.addAttribute("assets", assetService.returnAll());
        return"list";
    }
}


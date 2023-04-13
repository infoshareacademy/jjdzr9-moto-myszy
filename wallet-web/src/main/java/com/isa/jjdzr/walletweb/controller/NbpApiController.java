package com.isa.jjdzr.walletweb.controller;

import com.isa.jjdzr.walletweb.service.NbpApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class NbpApiController {

    private final NbpApiService apiNbpService;

    @GetMapping("/exchange-rates")
    public String getExchangeRates(Model model) {
        List<Map<String, Object>> ratesList = apiNbpService.getExchangeRates();
        model.addAttribute("exchangeRates", ratesList);
        return "exchange-rates";
    }
}
package com.yaroslavkislyi.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerRedirectController {

    @GetMapping("/swagger/index.html")
    public String redirectToSwagger() {
        return "redirect:/swagger";
    }
}

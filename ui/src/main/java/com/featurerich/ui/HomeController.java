package com.featurerich.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome() {

        return "com/featurerich/ui/templates/home.html";
    }
}

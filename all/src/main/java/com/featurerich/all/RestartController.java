package com.featurerich.all;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestartController {

    @GetMapping("/restart")
    public String restart() {

        System.out.println("OK");
        AllApplication.restart();

        return "Restarted;";
    }
}

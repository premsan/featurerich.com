package com.featurerich.application;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationRestartController {

    @GetMapping("/application/application-restart")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('APPLICATION_APPLICATION_RESTART')")
    public ModelAndView applicationRestartGet() {

        return new ModelAndView("com/featurerich/application/templates/application-restart");
    }

    @PostMapping("/application/application-restart")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('APPLICATION_APPLICATION_RESTART')")
    public ModelAndView applicationRestartPost() {

        BaseApplication.restartApplication();
        return new ModelAndView("redirect:/");
    }
}

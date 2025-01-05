package com.featurerich.base;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationRestartViewController {

    @FeatureMapping
    @GetMapping("/base/application-restart-view")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('BASE_APPLICATION_RESTART')")
    public ModelAndView applicationRestartGet() {

        return new ModelAndView("com/featurerich/base/templates/application-restart-view");
    }

    @PostMapping("/base/application-restart-view")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('BASE_APPLICATION_RESTART')")
    public ModelAndView applicationRestartPost() {

        BaseApplication.restartApplication();
        return new ModelAndView("redirect:/");
    }
}

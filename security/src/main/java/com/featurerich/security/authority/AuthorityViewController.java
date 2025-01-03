package com.featurerich.security.authority;

import com.featurerich.application.FeatureMapping;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AuthorityViewController {

    private final AuthorityRepository authorityRepository;

    @FeatureMapping
    @GetMapping("/security/authority-view/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView getAuthorityView(@PathVariable String id) {

        final Optional<Authority> optionalAuthority = authorityRepository.findById(id);

        if (optionalAuthority.isEmpty()) {

            return new ModelAndView("com/featurerich/ui/templates/not-found");
        }

        final ModelAndView modelAndView =
                new ModelAndView("com/featurerich/security/templates/authority-view");
        modelAndView.addObject("authority", optionalAuthority.get());

        return modelAndView;
    }
}

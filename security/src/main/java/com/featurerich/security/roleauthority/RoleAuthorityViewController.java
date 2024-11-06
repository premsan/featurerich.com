package com.featurerich.security.roleauthority;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class RoleAuthorityViewController {

    private final RoleAuthorityRepository roleAuthorityRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/security/role-authority-view/{id}")
    public ModelAndView getRoleAuthorityView(@PathVariable final String id) {

        Optional<RoleAuthority> roleAuthorityOptional = roleAuthorityRepository.findById(id);

        if (roleAuthorityOptional.isEmpty()) {

            return new ModelAndView("not-found");
        }

        final ModelAndView modelAndView =
                new ModelAndView("com/featurerich/security/templates/role-authority-view");
        modelAndView.addObject("roleAuthority", roleAuthorityOptional.get());

        return modelAndView;
    }
}

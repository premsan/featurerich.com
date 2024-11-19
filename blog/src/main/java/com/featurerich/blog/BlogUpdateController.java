package com.featurerich.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@RequestMapping("/blog/update")
public class BlogUpdateController {

    private final BlogRepository blogRepository;

    @GetMapping("/{id}")
    public ModelAndView getUpdate(@PathVariable String id) {

        final Blog blog = blogRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("blog-update");
        modelAndView.addObject("blog", new BlogRequest(id, blog.getTitle(), blog.getContent()));

        return modelAndView;
    }

    @PostMapping("/{id}")
    public View postUpdate(
            @AuthenticationPrincipal OidcUser principal, @ModelAttribute BlogRequest request) {
        final Blog blog = blogRepository.findById(request.getId()).get();
        blog.setTitle(request.getTitle());
        blog.setContent(request.getContent());
        blogRepository.save(blog);
        ModelAndView modelAndView = new ModelAndView("blog-update");
        modelAndView.addObject(
                "blog", new BlogRequest(blog.getId(), blog.getTitle(), blog.getContent()));

        return new RedirectView("" + blog.getId());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class BlogRequest {

        private String id;

        private String title;

        private String content;
    }
}

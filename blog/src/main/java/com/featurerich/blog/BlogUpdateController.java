package com.featurerich.blog;

import jakarta.validation.Valid;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BlogUpdateController {

    private final BlogRepository blogRepository;

    @GetMapping("/blog/blog-update")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('BLOG_UPDATE')")
    public ModelAndView getBlogUpdate(@PathVariable String id) {

        final Optional<Blog> optionalBlog = blogRepository.findById(id);

        if (optionalBlog.isEmpty()) {

            return new ModelAndView("not-found");
        }

        final ModelAndView modelAndView =
                new ModelAndView("com/featurerich/blog/templates/blog-update");

        final BlogUpdate blogUpdate = new BlogUpdate();
        blogUpdate.setTitle(optionalBlog.get().getTitle());
        blogUpdate.setContent(optionalBlog.get().getContent());

        modelAndView.addObject("blog", optionalBlog.get());
        modelAndView.addObject("blogUpdate", blogUpdate);

        return modelAndView;
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('BLOG_UPDATE')")
    public ModelAndView postBlogUpdate(
            @PathVariable String id,
            @Valid @ModelAttribute("blogUpdate") BlogUpdate blogUpdate,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @CurrentSecurityContext final SecurityContext securityContext) {

        final Optional<Blog> optionalBlog = blogRepository.findById(id);

        if (optionalBlog.isEmpty()) {

            return new ModelAndView("not-found");
        }

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {

            modelAndView.setViewName("com/featurerich/blog/templates/blog-update");
            modelAndView.addObject("blogUpdate", blogUpdate);

            return modelAndView;
        }

        optionalBlog.get().setTitle(blogUpdate.getTitle());
        optionalBlog.get().setContent(blogUpdate.getContent());
        optionalBlog.get().setUpdatedAt(System.currentTimeMillis());
        optionalBlog.get().setUpdatedBy(securityContext.getAuthentication().getName());

        redirectAttributes.addAttribute("id", id);
        return new ModelAndView("redirect:/blog/blog-view/{id}");
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class BlogUpdate {

        private String title;

        private String content;
    }
}

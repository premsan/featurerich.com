package com.featurerich.blog;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final Asciidoctor asciidoctor;

    private final BlogRepository blogRepository;

    @GetMapping("/blog/{id}")
    public ModelAndView view(
            @CurrentSecurityContext SecurityContext securityContext,
            final @PathVariable String id) {
        ModelAndView model = new ModelAndView("blog-view");

        final Blog blog = blogRepository.findById(id).get();

        model.addObject(
                "blog",
                new View(
                        blog.getId(),
                        blog.getVersion(),
                        blog.getTitle(),
                        asciidoctor.convert(blog.getContent(), Options.builder().build())));
        return model;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/blog/create")
    public ModelAndView getCreate(Authentication principal) {
        ModelAndView model = new ModelAndView("blog-create");
        model.addObject("blog", new CreateRequest());
        return model;
    }

    @PostMapping("/blog/create")
    public ModelAndView postCreate(@ModelAttribute CreateRequest request) {
        final Blog savedBlog =
                blogRepository.save(
                        new Blog(
                                UUID.randomUUID().toString(),
                                null,
                                request.getTitle(),
                                request.getContent()));
        ModelAndView model = new ModelAndView("blog-view");
        model.addObject("blog", savedBlog);
        return model;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class View {

        private String id;

        private Long version;

        private String title;

        private String content;
    }

    @Getter
    @Setter
    private static class CreateRequest {

        private String title;

        private String content;
    }
}

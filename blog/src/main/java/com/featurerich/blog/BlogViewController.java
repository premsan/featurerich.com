package com.featurerich.blog;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.asciidoctor.Options;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class BlogViewController {

    private final BlogApplicationContext applicationContext;

    private final BlogRepository blogRepository;

    @GetMapping("/blog/blog-view/{id}")
    public ModelAndView getBlogView(final @PathVariable String id) {

        final Optional<Blog> optionalBlog = blogRepository.findById(id);

        if (optionalBlog.isEmpty()) {

            return new ModelAndView("com/featurerich/ui/templates/not-found");
        }

        final ModelAndView model = new ModelAndView("com/featurerich/blog/templates/blog-view");

        model.addObject("blog", optionalBlog.get());
        model.addObject(
                "blogContentHtml",
                applicationContext
                        .getAsciidoctor()
                        .convert(optionalBlog.get().getContent(), Options.builder().build()));

        return model;
    }
}

package com.featurerich.json;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class JSONFormatController {

    @GetMapping("/json/json-format")
    public ModelAndView getJSONFormat() {

        final ModelAndView modelAndView =
                new ModelAndView("com/featurerich/json/templates/json-format");
        modelAndView.addObject("jsonFormat", new JSONFormat());

        return modelAndView;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class JSONFormat {

        @NotBlank private String input;
    }
}

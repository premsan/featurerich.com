package com.featurerich.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class JSONFormatController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/json/json-format")
    public ModelAndView getJSONFormat() {

        final ModelAndView modelAndView =
                new ModelAndView("com/featurerich/json/templates/json-format");
        modelAndView.addObject("jsonFormat", new JSONFormat());

        return modelAndView;
    }

    @PostMapping("/json/json-format")
    public ModelAndView postJSONFormat(
            @Valid @ModelAttribute("jsonFormat") JSONFormat jsonFormat,
            BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("com/featurerich/json/templates/json-format");
        modelAndView.addObject("jsonFormat", jsonFormat);

        if (bindingResult.hasErrors()) {

            return modelAndView;
        }

        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(jsonFormat.getInput());
        } catch (final JsonProcessingException e) {

            bindingResult.rejectValue("input", null, e.getOriginalMessage());

            return modelAndView;
        }

        modelAndView.addObject("output", jsonNode.toPrettyString());

        return modelAndView;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class JSONFormat {

        @NotBlank private String input;
    }
}

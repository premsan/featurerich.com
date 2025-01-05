package com.featurerich.application;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ModuleConfigurationController {

    private final Environment environment;

    private String messageFormat = "com/featurerich/{0}/config/{0}.properties";

    @GetMapping("/application/module-configuration/{module}")
    public ModelAndView moduleConfigurationGet(@PathVariable String module) throws IOException {

        final ModelAndView modelAndView =
                new ModelAndView("com/featurerich/application/templates/module-configuration-view");

        final Properties properties = new Properties();

        try (final InputStream inputStream =
                getClass()
                        .getClassLoader()
                        .getResourceAsStream(MessageFormat.format(messageFormat, module))) {

            final Properties loadedProperties = new Properties();
            loadedProperties.load(inputStream);

            for (final String key : loadedProperties.stringPropertyNames()) {

                properties.put(key, environment.getProperty(key));
            }
        }

        modelAndView.addObject("properties", properties);
        return modelAndView;
    }
}

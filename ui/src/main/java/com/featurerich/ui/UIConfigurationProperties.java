package com.featurerich.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "com.featurerich.ui")
public class UIConfigurationProperties {

    private Map<String, List<String>> links;

    public List<String> getAllLinks() {

        final List<String> allLinks = new ArrayList<>();

        if (links == null) {

            return allLinks;
        }

        for (final Map.Entry<String, List<String>> listEntry : links.entrySet()) {

            allLinks.addAll(listEntry.getValue());
        }

        return allLinks;
    }
}

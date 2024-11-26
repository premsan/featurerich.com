package com.featurerich.ui;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "com.featurerich.ui.links")
public class NavbarConfigurationProperties {

    private Map<String, List<String>> linksByModule;
}

package com.featurerich.featurerich;

import com.featurerich.application.BaseApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.featurerich")
public class FeatureRichApplication extends BaseApplication {

    public static void main(String[] args) {

        primarySource = FeatureRichApplication.class;
        context = SpringApplication.run(FeatureRichApplication.class, args);
    }
}

package com.featurerich.blog;

import static org.asciidoctor.Asciidoctor.Factory.create;

import org.asciidoctor.Asciidoctor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsciidoctorConfiguration {

    // @Bean
    Asciidoctor asciidoctor() {
        return create();
    }
}

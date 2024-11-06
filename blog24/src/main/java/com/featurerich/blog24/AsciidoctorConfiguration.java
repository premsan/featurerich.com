package com.featurerich.blog24;

import static org.asciidoctor.Asciidoctor.Factory.create;

import org.asciidoctor.Asciidoctor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsciidoctorConfiguration {

    @Bean
    Asciidoctor asciidoctor() {
        return create();
    }
}

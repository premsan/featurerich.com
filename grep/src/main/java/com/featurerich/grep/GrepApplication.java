package com.featurerich.grep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.featurerich")
public class GrepApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrepApplication.class, args);
    }
}
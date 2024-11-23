package com.featurerich.all;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.featurerich")
public class AllApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllApplication.class, args);
    }
}

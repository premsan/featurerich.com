package com.featurerich.all;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.featurerich")
public class AllApplication {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(AllApplication.class, args);
    }

    public static void restart()
    {

        final ApplicationArguments args = context.getBean(ApplicationArguments.class);

        final Thread thread =

                new Thread(
                () -> {

                    context.close();
                    context = SpringApplication.run(AllApplication.class, args.getSourceArgs());
                }
        );

        thread.setDaemon(false);
        thread.start();
    }
}

package com.netteans.examples.multiple.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

public class BootStrap {
    private static final Logger log = LoggerFactory.getLogger(BootStrap.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(BootStrap.class).banner((environment, sourceClass, out) -> {
            out.println("yo~~~~");
        }).run(args);
    }

    @Bean
    ApplicationRunner ar() {
        return args -> {
            log.info("laoded");
        };
    }
}

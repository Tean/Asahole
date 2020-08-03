package com.netteans.example.rabbitmq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author netteans
 */
@SpringBootApplication
public class BootStrap {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BootStrap.class);
        springApplication.setBanner((environment, sourceClass, out) -> {
            out.println("    ========     ======      //\\\\     ||    ||");
            out.println("       ||        ||         //  \\\\    ||\\\\  ||");
            out.println("       ||        ||===     //====\\\\   || \\\\ ||");
            out.println("       ||        ||        ||    ||   ||  \\\\||");
            out.println("       ||        ======    ||    ||   ||    ||");
        });
        springApplication.run(args);
    }

    @Bean
    public CommandLineRunner cr() {
        return args -> {
            System.out.println("cr run");
        };
    }
}

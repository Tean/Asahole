package com.netteans.example.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.netteans")
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
}

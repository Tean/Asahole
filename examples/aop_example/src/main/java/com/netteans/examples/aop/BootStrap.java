package com.netteans.example.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.netteans")
public class BootStrap {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BootStrap.class);
        springApplication.setBanner((environment, sourceClass, out) -> {
            out.println("    \033[31m========\033[0m     \033[33m======\033[0m    \033[34m  //\\\\\033[0m     \033[36m||    ||\033[0m");
            out.println("    \033[31m   ||   \033[0m     \033[33m||    \033[0m    \033[34m //  \\\\\033[0m    \033[36m||\\\\  ||\033[0m");
            out.println("    \033[31m   ||   \033[0m     \033[33m||=== \033[0m    \033[34m//____\\\\\033[0m   \033[36m|| \\\\ ||\033[0m");
            out.println("    \033[31m   ||   \033[0m     \033[33m||    \033[0m    \033[34m||----||\033[0m   \033[36m||  \\\\||\033[0m");
            out.println("    \033[31m   ||   \033[0m     \033[33m======\033[0m    \033[34m||    ||\033[0m   \033[36m||    ||\033[0m");
        });
        springApplication.run(args);
    }
}

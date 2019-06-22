package com.netteans.example.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
//@ComponentScans({@ComponentScan("com.netteans.example.shiro.controller")})
public class BootStrap {

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }
}

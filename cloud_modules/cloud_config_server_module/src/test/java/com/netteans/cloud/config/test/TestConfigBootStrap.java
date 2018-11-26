package com.netteans.cloud.config.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class TestConfigBootStrap {
    public static void main(String[] args) {
        SpringApplication.run(TestConfigBootStrap.class, args);
    }
}

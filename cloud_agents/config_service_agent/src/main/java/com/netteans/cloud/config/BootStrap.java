package com.netteans.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BootStrap {
    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }
}

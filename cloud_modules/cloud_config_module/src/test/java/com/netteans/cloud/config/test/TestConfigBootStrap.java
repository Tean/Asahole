package com.netteans.cloud.config.test;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringCloudApplication
public class TestConfigBootStrap {
    public static void main(String[] args) {
        SpringApplication.run(TestConfigBootStrap.class, args);
    }
}

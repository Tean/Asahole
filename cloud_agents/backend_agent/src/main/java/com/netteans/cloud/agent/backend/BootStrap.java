package com.netteans.cloud.agent.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringCloudApplication
public class BootStrap {
    private static Logger logger = LoggerFactory.getLogger(BootStrap.class);

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }
}

package com.netteans.cloud.agent.zookeeper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication

public class BootStrap {
    private static Logger logger = LoggerFactory.getLogger(BootStrap.class);
    private static String port;

    @Value("${server.port}")
    private void setPort(String p) {
        port = p;
    }

    public static void main(String[] args) {
        logger.info("zkinstance port is: {}", port);
        SpringApplication.run(BootStrap.class, args);
    }
}

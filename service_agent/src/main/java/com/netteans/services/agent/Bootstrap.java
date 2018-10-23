package com.netteans.services.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

public class Bootstrap {
    private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) {
        logger.debug("Bootstrap Run");
        SpringApplication.run(Bootstrap.class, args);
    }
}

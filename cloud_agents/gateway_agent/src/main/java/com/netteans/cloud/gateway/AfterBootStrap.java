package com.netteans.cloud.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AfterBootStrap implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AfterBootStrap.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("{}","server ready");
        logger.debug("{}", args);
    }
}

package com.netteans.cloud.exposed.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AfterBootSTrap implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(BootStrap.class);

    @Autowired
    private ApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        String[] profiles = context.getEnvironment().getActiveProfiles();
        if (profiles.length == 0) {
            profiles = context.getEnvironment().getDefaultProfiles();
        }
        logger.info("active profile is {}", profiles);
        for (String arg :
                args) {
            String[] kv = arg.split("=");
            logger.info(String.format("load params: %s -> %s", kv.length > 0 ? kv[0] : "error", kv.length > 1 ? kv[1] : "none"));
        }
        logger.info("started & load server.port:" + 000);
    }
}

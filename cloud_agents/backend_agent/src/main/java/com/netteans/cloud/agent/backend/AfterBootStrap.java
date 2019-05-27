package com.netteans.cloud.agent.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AfterBootStrap implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(AfterBootStrap.class);

    @Autowired
    private BeanFactory beanFactory;

    @Value("${server.port}")
    private String port;

    @Override
    public void run(String... args) throws Exception {
        for (String arg :
                args) {
            String[] kv = arg.split("=");
            logger.info(String.format("load params: %s -> %s", kv.length > 0 ? kv[0] : "error", kv.length > 1 ? kv[1] : "none"));
        }
        logger.info("zkinstance port is: {}", port);
    }
}

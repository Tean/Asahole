package com.netteans.agent.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class Bootstrap {
    private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) {
        logger.debug("Bootstrap Run @ {}", new File("./").getAbsolutePath());
        SpringApplication.run(Bootstrap.class, args);
    }
}

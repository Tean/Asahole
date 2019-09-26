package com.netteans.agent.service;

import com.netteans.common.config.IServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;

@SpringBootApplication
public class Bootstrap {
        private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

        public static void main(String[] args) {
            try {
                Class c = Class.forName("com.netteans.agent.service.Bootstrap");
                Class c1 = Class.forName("com.netteans.agent.service.test1.ForName");
                Class c2 = Class.forName("com.netteans.agent.service.test2.ForName");
                logger.info(c.toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            logger.info("Bootstrap Run @ {}", new File("./").getAbsolutePath());
            SpringApplication.run(Bootstrap.class, args);
        }

    @Autowired
    IServiceConfig serviceConfig;

    @Bean
    ApplicationRunner ar() {
        return args -> {
            System.out.println(serviceConfig);
        };
    }
}

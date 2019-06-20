package com.netteans.dubbo.example.starter.consumer;

import com.netteans.dubbo.example.service.IAction;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
public class BootStrap {
    private static final Logger logger = LoggerFactory.getLogger(BootStrap.class);

    @Reference(version = "${demo.service.version}")
    private IAction aservice;

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            logger.info(aservice.getOne("adDetail").toString());
            logger.info(aservice.getList().toString());
        };
    }
}

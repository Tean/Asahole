package com.netteans.agent;

import com.netteans.agent.service.LocaleService;
import com.netteans.common.config.IServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class Bootstrap {
        private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) {
        logger.info("Bootstrap Run @ {}", new File("./").getAbsolutePath());
        SpringApplication.run(Bootstrap.class, args);
    }

    @Autowired
    IServiceConfig serviceConfig;

    @Autowired
    LocaleService localeService;

    @Bean
    ApplicationRunner ar() {
        return args -> {
            System.out.println(localeService.getLocale());
            System.out.println(serviceConfig);
        };
    }
}

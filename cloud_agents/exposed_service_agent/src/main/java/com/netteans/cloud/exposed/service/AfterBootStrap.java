package com.netteans.cloud.exposed.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AfterBootStrap implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(BootStrap.class);

    @Autowired
    private ApplicationContext context;

    @Value("${server.port}")
    private String port;

    @Autowired
    WebMvcConfig config;

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
        logger.info("started & load server.port:" + port);
        IA al = a -> {
            System.out.println(port);
            System.out.println(this.getClass());
            System.out.println(a.getClass());
        };

        IA ap = new IA() {
            @Override
            public void a(IA a) {
                System.out.println(port);
                System.out.println(this.getClass());
                System.out.println(a.getClass());
            }
        };
        al.a(al);
        ap.a(ap);
    }
}


interface IA {
    void a(IA a);
}

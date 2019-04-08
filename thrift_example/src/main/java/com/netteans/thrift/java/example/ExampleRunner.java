package com.netteans.thrift.java.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ExampleRunner implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(ExampleRunner.class);

    @Autowired
    private ExampleConfigure exampleConfigure;

    @Autowired
    private ExampleServer exampleServer;

    @Autowired
    private ApplicationContext context;

    public void run(String... args) throws Exception {
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            Object bean = context.getBean(beanDefinitionName);
            String beanClassName =bean.getClass().getName();
            if (!beanClassName.startsWith("org.springframework")) {
                logger.debug("context definition beans name {}", beanClassName);
            }
        }
        logger.debug("Server start @ port: {}", exampleConfigure.getPort());
        exampleServer.asyncRun(exampleConfigure.getPort());
        logger.debug("{}", "server start asynced");
    }
}

package com.netteans.cloud.explosed.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@SpringCloudApplication
public class BootStrap {
    private static Logger logger = LoggerFactory.getLogger(BootStrap.class);
    private static String port;

//    @Value("${server.port}")
    private void setPort(String p) {
        port = p;
    }

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
        logger.info("port is {}", port); //启动之后才会注入
    }
}

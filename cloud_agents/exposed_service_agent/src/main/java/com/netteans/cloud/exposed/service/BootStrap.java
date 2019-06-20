package com.netteans.cloud.exposed.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@SpringCloudApplication
@EnableCircuitBreaker
public class BootStrap {
    private static Logger logger = LoggerFactory.getLogger(BootStrap.class);

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }
}

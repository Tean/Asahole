package com.netteans.cloud.exposed.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AnnotatedConfiguration {

    @Autowired
    private RemoteConfig remoteConfig;

    @Bean
    @Profile("node1")
    RemoteConfig getRemoteConfig1() {
        System.out.println("prof node1");
        return remoteConfig;
    }

    @Bean
    @Profile("node2")
    RemoteConfig getRemoteConfig2() {
        System.out.println("prof node2");
        return remoteConfig;
    }
}

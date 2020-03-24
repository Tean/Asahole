package com.netteans.thrift.java.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ExampleConfigure {
    @Value("${thrift.port}")
    private int port;

    private ISet setEvent = (oldValue, newValue) -> System.out.println(oldValue + " => " + newValue);


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }


    public int getPort() {
        return port;
    }
}

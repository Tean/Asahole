package com.netteans.thrift.java.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    @Value("thrift.port")
    private static int port;

    public static void main(String[] args) {
        System.out.println("port is: " + port);
        SpringApplication.run(Application.class, args);
        System.out.println("port is: " + port);
    }
}
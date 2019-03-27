package com.netteans.thrift.java.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class Bootstrap {

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    @Autowired
    private static ExampleConfigure exampleConfigure;

    public static void main(String[] args) {
        logger.error("error test");
        System.out.println("cp is " + ClassLoader.getSystemResource(".").getPath());
        try {
            Files.list(Paths.get(ClassLoader.getSystemResource(".").getPath())).forEach(path -> {
                System.out.println(path);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("port is: " + ExampleConfigure.getPort()); //获取不到配置文件的port
        SpringApplication.run(Bootstrap.class, args);
        System.out.println("port is: " + ExampleConfigure.getPort()); //获取得到application.yml的port，需要spring-cloud-context才能获取到bootstrap.yml的port，如果application.yml有重复字段则会优先使用
    }
}
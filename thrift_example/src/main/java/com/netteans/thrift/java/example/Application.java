package com.netteans.thrift.java.example;

import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    @Autowired
    private static ExampleConfigure exampleConfigure;

    public static void main(String[] args) {
        System.out.println("cp is " + ClassLoader.getSystemResource(".").getPath());
        System.out.println("port is: " + ExampleConfigure.getPort()); //获取不到配置文件的port
        SpringApplication.run(Application.class, args);
        System.out.println("port is: " + ExampleConfigure.getPort()); //获取得到application.yml的port，需要spring-cloud-context才能获取到bootstrap.yml的port，如果application.yml有重复字段则会优先使用
    }
}
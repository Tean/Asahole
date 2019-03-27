package com.netteans.thrift.java.example;

import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExampleRunner implements CommandLineRunner {

    @Autowired
    private ExampleServer exampleServer;

    public void run(String... args) throws Exception {
        try {
            exampleServer.run(ExampleConfigure.getPort());
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}

package com.netteans.example.elk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AfterBootStrap implements CommandLineRunner {

    @Autowired
    private ElasticsearchConfig esc;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(esc);
    }
}

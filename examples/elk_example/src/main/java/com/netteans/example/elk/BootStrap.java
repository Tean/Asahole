package com.netteans.example.elk;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class BootStrap {
    private static final Logger logger = LoggerFactory.getLogger(BootStrap.class);

    @Autowired
    RestHighLevelClient client;

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args).close();
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            logger.info(String.valueOf(isIndexExist("logstash*", ".newkibana*")));
        };
    }

    public boolean isIndexExist(String... index) throws IOException {
        boolean exists = client.indices().exists(new GetIndexRequest(index), RequestOptions.DEFAULT);
        return exists;
    }
}

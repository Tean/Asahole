package com.netteans.example.mybatis;

import com.netteans.example.mybatis.dao.model.ExampleMessage;
import com.netteans.example.mybatis.dao.model.ExampleUser;
import com.netteans.example.mybatis.service.ExampleService;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@MapperScans({@MapperScan("com.netteans.example.mybatis.dao.mapper")})
public class BootStrap {
    private final static Logger logger = LoggerFactory.getLogger(BootStrap.class);

    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    @Autowired
    DataSource ds;

    @Autowired
    ExampleService exampleService;

    public static void main(String[] args) {
        logger.info("{}", "args:" + Arrays.toString(args));
        SpringApplication.run(BootStrap.class, args);
    }

    @Bean
    public CommandLineRunner cmdrun() {
        ExampleUser user = exampleService.getUser(1);
        if (user == null) {
            logger.info("{}", "add" + exampleService.addUser(new ExampleUser().setName("wxm").setPassword("1234").setSalt("Salty")));
        }
        return args -> {
            logger.info("{}", "CommandLineRunner");
            threadPoolExecutor.execute(() -> {
                while (true) {
                    logger.info("{}", "args:" + Arrays.toString(args));
                    logger.info("{}", ds.getClass().getName());
                    Connection connection;
                    try {
                        connection = ds.getConnection();
                        logger.info("{}", connection.getClass().getName() + "@" + connection);
                        logger.info("{}", connection.getSchema());
                        connection.close();
                        logger.info("{}", exampleService.getUser(1));
                        TimeUnit.SECONDS.sleep(5);
                    } catch (SQLException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        };
    }

    @Bean
    public ApplicationRunner run() {
        return args -> {
            logger.info("{}", "ApplicationRunner");
            threadPoolExecutor.execute(() -> {
                while (true) {
                    logger.info("{}", "args:" + Arrays.toString(args.getSourceArgs()));
                    logger.info("{}", ds.getClass().getName());
                    Connection connection;
                    try {
                        connection = ds.getConnection();
                        logger.info("{}", connection.getClass().getName() + "@" + connection);
                        connection.close();
                        TimeUnit.SECONDS.sleep(60);
                    } catch (SQLException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        };
    }
}


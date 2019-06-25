package com.netteans.example.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
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

    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    @Autowired
    DataSource ds;

    public static void main(String[] args) {
        System.out.println("args:" + Arrays.toString(args));
        SpringApplication.run(BootStrap.class, args);
    }

    @Bean
    public CommandLineRunner cmdrun() {
        return args -> {
            System.out.println("CommandLineRunner");
            threadPoolExecutor.execute(() -> {
                while (true) {
                    System.out.println("args:" + Arrays.toString(args));
                    System.out.println(ds.getClass().getName());
                    Connection connection;
                    try {
                        connection = ds.getConnection();
                        System.out.println(connection.getClass().getName() + "@" + connection);
                        connection.close();
                        TimeUnit.SECONDS.sleep(1);
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
            System.out.println("ApplicationRunner");
            threadPoolExecutor.execute(() -> {
                while (true) {
                    System.out.println("args:" + Arrays.toString(args.getSourceArgs()));
                    System.out.println(ds.getClass().getName());
                    Connection connection;
                    try {
                        connection = ds.getConnection();
                        System.out.println(connection.getClass().getName() + "@" + connection);
                        connection.close();
                        TimeUnit.SECONDS.sleep(1);
                    } catch (SQLException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        };
    }
}


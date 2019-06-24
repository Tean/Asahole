package com.netteans.example.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@MapperScans({@MapperScan("com.netteans.example.mybatis.dao.mapper")})
public class BootStrap {

    @Autowired
    DataSource ds;

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class);
    }

    @Bean
    public ApplicationRunner run() {
        return args -> {
            while (true) {
                System.out.println("args:" + args.getSourceArgs());
                System.out.println(ds.getClass().getName());
                Connection connection = ds.getConnection();
                System.out.println(connection.getClass().getName() + "@" + connection);
                connection.close();
                TimeUnit.SECONDS.sleep(1);
            }
        };
    }
}

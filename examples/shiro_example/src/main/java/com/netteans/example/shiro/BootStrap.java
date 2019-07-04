package com.netteans.example.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
//@ComponentScans({@ComponentScan("com.netteans.example.shiro.controller")})
public class BootStrap {

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }

    @Bean
    public ApplicationRunner ar() {
        HashSet set = new HashSet();
        set.spliterator();
        System.out.println("ar");
        List<String> s = new LinkedList<String>();
        return args -> {
            int[] arr = new int[]{1, 2, 3, 4, 5};
            for (int i : arr) {
                int finalI = i;
                new Thread(() -> {
                    System.out.println(finalI);
                    arr[i-1] = 54;
                }).start();
            }
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Arrays.toString(arr));
        };
    }

    private IPy p = new Zpy();
}

class Py implements IPy {
    static {
        System.out.println("init static py");
    }

    private p p = new p(1);

    public Py() {
        System.out.println("init py");
    }
}

class Zpy extends Py {
    static {
        System.out.println("init static zpy");
    }

    private p p = new p(2);

    public Zpy() {
        System.out.println("init zpy");
    }
}

class p {
    public p(int x) {
        System.out.println("init p " + x);
    }
}


package com.netteans.example.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
//@ComponentScans({@ComponentScan("com.netteans.example.shiro.controller")})
@EnableScheduling
public class BootStrap {
    public static final Logger SCHEDULER = LoggerFactory.getLogger("scheduler");
    public static final Logger HISTORY = LoggerFactory.getLogger("history");

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

    @Scheduled(fixedRateString = "${app.scheduled.rate}")
    public void schedule() {
        SCHEDULER.trace("trace");
        SCHEDULER.info("info");
        SCHEDULER.debug("debug");
        SCHEDULER.warn("warn");
        SCHEDULER.error("error");
        HISTORY.trace("trace");
        HISTORY.info("info");
        HISTORY.debug("debug");
        HISTORY.warn("warn");
        HISTORY.error("error");
    }
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


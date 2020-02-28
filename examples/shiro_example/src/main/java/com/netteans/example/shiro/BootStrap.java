package com.netteans.example.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
//@ComponentScans({@ComponentScan("com.netteans.example.shiro.controller")})
@EnableScheduling
public class BootStrap {
    private static final Logger logger = LoggerFactory.getLogger(BootStrap.class);
    private static final Logger SCHEDULER = LoggerFactory.getLogger("scheduler");
    private static final Logger HISTORY = LoggerFactory.getLogger("history");

    @Autowired
    private IPy py1;

    @Autowired
    private IPy py2;

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }

    @Bean
    public ApplicationRunner ar() {
        List s;
        Map<String, String> ma = new HashMap<>(2);
        System.out.println("trace: " + logger.isTraceEnabled());
        System.out.println("debug: " + logger.isDebugEnabled());
        System.out.println("info: " + logger.isInfoEnabled());
        System.out.println("warn: " + logger.isWarnEnabled());
        System.out.println("error: " + logger.isErrorEnabled());
        HashSet set = new HashSet();
        Spliterator spliterator = set.spliterator();
        System.out.println("ar bean");
        return args -> {
            int[] arr = new int[]{1, 2, 3, 4, 5};
            for (int i : arr) {
                int finalI = i;
                new Thread(() -> {
                    System.out.println(finalI);
                    arr[i - 1] = 54;
                }).start();
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Arrays.toString(arr));
            Exception e = new NoSuchElementException("just test");
            logger.trace("ar trace");
            logger.info("ar info");
            logger.debug("ar debug");
            logger.warn("ar warn");
            logger.error("ar error", e);
            SCHEDULER.trace("ar trace");
            SCHEDULER.info("ar info");
            SCHEDULER.debug("ar debug");
            SCHEDULER.warn("ar warn");
            SCHEDULER.error("ar error");
            HISTORY.trace("ar trace");
            HISTORY.info("ar info");
            HISTORY.debug("ar debug");
            HISTORY.warn("ar warn");
            HISTORY.error("ar error");
        };
    }

    private IPy p = new Zpy();

    private DelayQueue<EnclosePacket> enclosePackets = new DelayQueue<EnclosePacket>();

    @Scheduled(fixedRateString = "${app.scheduled.rate}")
    public void schedule() {
//        SCHEDULER.trace("trace");
//        SCHEDULER.debug("debug");
        SCHEDULER.info("info");
//        SCHEDULER.warn("warn");
//        SCHEDULER.error("error");
//        HISTORY.trace("trace");
//        HISTORY.debug("debug");
        HISTORY.info("info");
//        HISTORY.warn("warn");
//        HISTORY.error("error");


    }
}

class EnclosePacket implements Delayed {

    private final static long CREATED_NANO = System.nanoTime();

    private final long outed;

    EnclosePacket(long outed, TimeUnit tu) {
        this.outed = outed;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(System.nanoTime() - CREATED_NANO, TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}

//@Component
@Scope("prototype")
class Py implements IPy {
    static {
        System.out.println("init static py");
    }

    private P p = new P(1);

    public Py() {
        System.out.println("init py");
    }
}

@Component
@Scope("singleton")
class Zpy extends Py {
    static {
        System.out.println("init static zpy");
    }

    private P p = new P(2);

    public Zpy() {
        System.out.println("init zpy");
    }
}

class P {
    public P(int x) {
        System.out.println("init p " + x);
    }
}


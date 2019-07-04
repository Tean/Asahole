package com.netteans.example.weakmonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;

import java.lang.ref.WeakReference;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class BootStrap {

    private Logger logger = LoggerFactory.getLogger(BootStrap.class);

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }

    @Bean
    ApplicationRunner ar() {
        ConcurrentHashMap<String, String> vvm = new ConcurrentHashMap<>(10); //capacity 决定size为比capacity大的最小的2的n次幂
        vvm.put("0", "0");
        vvm.put("1", "1");
        vvm.put("2", "2");
        vvm.put("3", "3");
        vvm.put("4", "4");
        vvm.put("5", "5");
        vvm.put("6", "6");
        vvm.put("7", "7");
        vvm.put("8", "8");
        vvm.put("9", "9");
        vvm.put("10", "10");
        vvm.put("11", "11");
        vvm.put("12", "12");
        vvm.put("13", "13");
        vvm.put("14", "14");
        vvm.put("15", "15");
        vvm.put("16", "16");
        vvm.put("17", "17");
        vvm.put("18", "18");
        vvm.put("19", "19");
        vvm.put("20", "20");
        vvm.put("21", "21");
        vvm.put("22", "22");
        vvm.put("23", "23");
        vvm.put("24", "24");
        vvm.put("25", "25");
        vvm.put("26", "26");
        vvm.put("27", "27");
        vvm.put("28", "28");
        vvm.put("29", "29");

        vvm.elements();

        logger.info("{}", "app run");
        WeakReference<Hello> weakReference = new WeakReference<Hello>(new Hello());
        weakReference.get().init();
        return args -> {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                    4,
                    4,
                    10,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(4));
            threadPoolExecutor.execute(() -> {
                int count = 0;
                while (weakReference.get() != null) {
                    logger.info("whello!,{}", count);
                    if ((count++ % 10 == 0) && count > 10) {
                        System.gc();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                logger.info("deal");
            });
        };
    }
}

package com.netteans.examples.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ExampleClassLoader extends ClassLoader {
    private static final Logger logger = LoggerFactory.getLogger(ExampleClassLoader.class);

    ThreadLocal<String> s = new ThreadLocal<>();

    public ExampleClassLoader() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        logger.info("{}", "start");
        Future<Integer> submit = executorService.submit((() -> {
            TimeUnit.SECONDS.sleep(5);
            return 28;
        }));
        while (!submit.isDone() && !submit.isCancelled()) {
            try {
                logger.info("{}", "waiting");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            logger.info("{}", submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

package com.netteans.examples.restswagger;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class BootStrap {
    public static void main(String[] args) {

//        for (int i = 0; i < 20; i++) {
//            Thread t = new Thread(() -> {
//                Singleton instance = Singleton.getInstance();
//                instance.print();
//                System.out.println("Thread:" + Thread.currentThread().getId() + " get " + instance);
//            });
//            t.start();
//        }

        BlockingQueue queue = new SynchronousQueue();
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Factoried-Thread-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 1, TimeUnit.SECONDS, queue, threadFactory);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
//                Singleton instance = Singleton.getInstance();
//                System.out.println("ThreadPoolExecutor:" + Thread.currentThread().getId() + " get " + instance);
                System.out.println("ThreadPoolExecutor:" + Thread.currentThread().getName() + " end");
            });
        }
        System.out.println("Main:" + Thread.currentThread().getId() + " end");
    }
}

class Singleton {
    private static volatile Singleton instance = null;

    public Singleton() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void print() {
        System.out.println(this + " wow!");
    }
}

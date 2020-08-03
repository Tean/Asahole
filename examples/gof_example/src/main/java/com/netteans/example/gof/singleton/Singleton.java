package com.netteans.example.gof.singleton;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author netteans
 */
public class Singleton implements Runnable {
    private static final AtomicBoolean INITIAL = new AtomicBoolean(false);
    /**
     * 保证getInstance获取的都是最新的instance，即防止返回初始化过程中的instance
     */
    private static volatile Singleton instance = null;
    private ThreadLocal<Object> localVar = new ThreadLocal<>();

    private Singleton() {
        synchronized (Singleton.class) {
            if (INITIAL.get()) {
                throw new RuntimeException("donot reflection");
            }
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                    INITIAL.set(true);
                }
            }
        }
        return instance;
    }

    @Override
    public void run() {
        localVar.set(Thread.currentThread().getId());
        System.out.println("Run @ Thread~ " + Thread.currentThread().getId() + " with localvar: " + localVar.get().toString());
        int i = new Random(System.nanoTime()).nextInt(10000);
        System.out.println("Thread~ " + Thread.currentThread().getId() + " sleep in " + i + " milliseconds");
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

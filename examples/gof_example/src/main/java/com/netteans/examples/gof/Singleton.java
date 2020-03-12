package com.netteans.examples.gof;

import java.util.concurrent.atomic.AtomicBoolean;

public class Singleton {
    private static AtomicBoolean inited = new AtomicBoolean(false);
    private static volatile Singleton instance = null;//保证getInstance获取的都是最新的instance，即防止返回初始化过程中的instance

    private Singleton() {
        synchronized (Singleton.class) {
            if (inited.get()) {
                throw new RuntimeException("donot reflection");
            }
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                    inited.set(true);
                }
            }
        }
        return instance;
    }
}

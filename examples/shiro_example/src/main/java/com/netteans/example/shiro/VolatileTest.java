package com.netteans.example.shiro;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class VolatileTest {
    public static void main(String[] args) {
        boolean flag = false;
//        Ref<Boolean> ref = new Ref<>();
        AtomicBoolean atomicBoolean = new AtomicBoolean();

        MyThread t = new MyThread(atomicBoolean);
        t.start();

        while (true) {
            try {
                TimeUnit.NANOSECONDS.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (atomicBoolean.get()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("in main loop");
            }
        }
    }
}

class MyThread extends Thread {

    private final AtomicBoolean ref;

    public MyThread(AtomicBoolean ref) {
        this.ref = ref;
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.ref.set(!this.ref.get());
            System.out.println("toggle flag " + (this.ref.get() ? "on" : "off"));
        }
    }
}
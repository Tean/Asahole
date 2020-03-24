package com.netteans.examples.weakmonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.lang.ref.WeakReference;

// ref from: http://ju.outofmemory.cn/entry/134992
// but it's 并没有什么卵用
public class Hello {
    private boolean inited;
    private Thread thread;
    private static Logger logger = LoggerFactory.getLogger(Hello.class);

    public void close() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    @PostConstruct
    public synchronized void init() {
        if(inited){
            return;
        }
        Monitor monitor = new Monitor(this);
        thread = new Thread(monitor);
        thread.setDaemon(true);
        thread.start();
        inited = true;
    }

    static class Monitor implements Runnable {
        private WeakReference<Hello> ref;

        public Monitor(Hello hello) {
            this.ref = new WeakReference<Hello>(hello);
        }

        private Hello getHello() throws WeakReferenceGCException {
            Hello weak = ref.get();
            if (weak == null) {
                throw new WeakReferenceGCException();
            }
            return weak;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(100);
                    Hello hello = getHello();
                    hello.shutdown();
                } catch (WeakReferenceGCException e) {
                    System.out.println("Hello has be GC");
                    break;
                } catch (InterruptedException ignore) {
                    break;
                }
            }
        }
    }

    private void shutdown() {
        System.out.println("manual shutdown");
    }
}

class WeakReferenceGCException extends RuntimeException {
}
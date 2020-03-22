package com.netteans.examples.gof;

import com.netteans.examples.gof.absfactory.AbstractFactoryMethod;
import com.netteans.examples.gof.absfactory.Animal;
import com.netteans.examples.gof.absfactory.IFactory;
import com.netteans.examples.gof.absfactory.ISuit;
import com.netteans.examples.gof.factory.*;
import com.netteans.examples.gof.factory.contract.ISampleFactory;
import com.netteans.examples.gof.proxy.IProxyImpl;
import com.netteans.examples.gof.proxy.Proxy;
import com.netteans.examples.gof.singleton.Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;

public class BootStrap {

    public static void main(String[] args) {
//        testSingleton();
//        testPrototype();
//        testFactoryMethod();
//        testAbstractFactoryMethod();
//        testBuilder();
//        testProxy();
//        testAdapter();
//        testBridge();
        FutureTask<String> taskFuture = new FutureTask<String>(() -> {
            TimeUnit.SECONDS.sleep(5);
            return "done";
        });

        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        executor.execute(taskFuture);

        try {
            System.out.println(System.currentTimeMillis());
            String s = null;
            try {
                s = taskFuture.get(3, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis());
            System.out.println(s);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}

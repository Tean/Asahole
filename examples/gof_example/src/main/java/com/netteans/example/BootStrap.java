package com.netteans.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BootStrap {

    public static void main(String[] args) {
//        testSingleton();
//        testPrototype();
//        testFactoryMethod();
//        testAbstractFactoryMethod();
//        testBuilder();
//        testProxy();
//        testAdapter();
        testBridge();
    }

    private static void testBridge() {
        System.out.println(new Bridge(new Tom(), new AdminRole()).bridged());
        System.out.println(new Bridge(new Dami(), new GunsterRole()).bridged());
    }

    private static void testAdapter() {
        AdapterExecutor adapterExecutor = new AdapterExecutor();
        adapterExecutor.injectAdapter(new CatAdapter(new Cat()));
        System.out.println(adapterExecutor.exec());
        adapterExecutor.injectAdapter(new DogAdapter(new Dog()));
        System.out.println(adapterExecutor.exec());
    }

    private static void testProxy() {
        Proxy.Static staticProxy = new Proxy.Static();
        staticProxy.setDelegate(new IProxyImpl("static"));
        System.out.println(staticProxy.proxyMethod());

        Proxy.Dynamic dynamicProxy = new Proxy.Dynamic();
        dynamicProxy.setDelegate(new IProxyImpl("dynamic"));
        try {
            System.out.println(dynamicProxy.invoke(IProxyImpl.class.getMethod("proxyMethod")));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void testBuilder() {
        Builder builder = new Builder();
        builder.addPart(() -> "part1");
        System.out.println(builder.build().getDetail());
        builder.addPart(() -> "part2");
        System.out.println(builder.build().getDetail());
        builder.addPart(() -> "part3");
        System.out.println(builder.build().getDetail());
    }

    private static void testAbstractFactoryMethod() {
        System.out.println(AbstractFactoryMethod.getSuit("animal").getSuit().getDetail());
        System.out.println(AbstractFactoryMethod.getSuit("sj").getSuit().getDetail());
    }

    private static void testFactoryMethod() {
        System.out.println(FactoryMethod.genSuit("new").name());
        System.out.println(FactoryMethod.genSuit("x").name());
    }

    public static void testPrototype() {
        Prototype prototype = new Prototype();
        System.out.println(prototype);
        System.out.println(prototype.clone());
    }

    public static void testSingleton() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4,
                4,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5));
        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println(Singleton.getInstance());
                try {
                    Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    Object o = constructor.newInstance();
                    System.out.println(o);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

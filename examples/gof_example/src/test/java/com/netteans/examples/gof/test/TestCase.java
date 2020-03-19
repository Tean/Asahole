package com.netteans.examples.gof.test;

import com.netteans.examples.gof.*;
import com.netteans.examples.gof.absfactory.AbstractFactoryMethod;
import com.netteans.examples.gof.absfactory.Animal;
import com.netteans.examples.gof.absfactory.IFactory;
import com.netteans.examples.gof.absfactory.ISuit;
import com.netteans.examples.gof.factory.*;
import com.netteans.examples.gof.factory.contract.ISampleFactory;
import com.netteans.examples.gof.proxy.IProxy;
import com.netteans.examples.gof.proxy.IProxyImpl;
import com.netteans.examples.gof.proxy.Proxy;
import com.netteans.examples.gof.singleton.Singleton;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;

public class TestCase {

    @Before
    public void before() {
        System.out.println("@before");
//        System.out.println(Sort.testArr);
    }

    @Test
    public void test() {
//        Sort.sortedArr = Sort.bubble(Sort.testArr);
//        Sort.sortedArr = Sort.selection(Sort.testArr);
    }

    @Test
    public void testSingleton() {
        int deduce = 10;

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(deduce, deduce * 2, 5000, TimeUnit.MICROSECONDS, new LinkedBlockingDeque<>());
        CountDownLatch countDownLatch = new CountDownLatch(deduce);
        Runnable[] srounds = new Runnable[deduce];
        for (int i = 0; i < srounds.length; i++) {
            Runnable sround = new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.countDown();
                        System.out.println("countdown:" + countDownLatch.getCount());
                        countDownLatch.await();
                        System.out.println("ok:" + countDownLatch.getCount());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Singleton.getInstance().run();
                }
            };
            threadPoolExecutor.execute(sround);
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBridge() {
        System.out.println(new Bridge(new Tom(), new AdminRole()).bridged());
        System.out.println(new Bridge(new Dami(), new GunsterRole()).bridged());
    }

    @Test
    public void testAdapter() {
        AdapterExecutor adapterExecutor = new AdapterExecutor();
        adapterExecutor.injectAdapter(new CatAdapter(new Cat()));
        System.out.println(adapterExecutor.exec());
        adapterExecutor.injectAdapter(new DogAdapter(new Dog()));
        System.out.println(adapterExecutor.exec());
    }

    @Test
    public void testProxy() {
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

        IProxy proxy = Proxy.JDKDynamicProxyFactory.newInstance(IProxy.class);
        System.out.println(proxy.toString());
        Object o = proxy.proxyMethod();
        System.out.println(o);
    }

    @Test
    public void testBuilder() {
        Builder builder = new Builder();
        builder.addPart(() -> "part1");
        System.out.println(builder.build().getDetail());
        builder.addPart(() -> "part2");
        System.out.println(builder.build().getDetail());
        builder.addPart(() -> "part3");
        System.out.println(builder.build().getDetail());
    }

    @Test
    public void testAbstractFactoryMethod() {
        SampleGroupFactory integerInvokeFactoryGroup = SampleGroupFactory.getInstance();
        IntegerInvokeFactory integerInvokeFactory = integerInvokeFactoryGroup.getSomeSampleFactory(IntegerInvokeFactory.class);
        IntegerSampleImpl integerSampleImpl = integerInvokeFactory.getSampleImpl();
        Integer intSample = integerSampleImpl.getSample();
        integerSampleImpl.dosth(1);

        SampleGroupFactory stringInvokeFactoryGroup = SampleGroupFactory.getInstance();
        StringInvokeFactory stringInvokeFactory = stringInvokeFactoryGroup.getSomeSampleFactory(StringInvokeFactory.class);
        StringSampleImpl stringSampleImpl = stringInvokeFactory.getSampleImpl();
        String stringSample = stringSampleImpl.getSample();
        stringSampleImpl.dosth("ohooh");

        SampleGroupFactory objInvokerFactoryGroup = SampleGroupFactory.getInstance();
        ISampleFactory<ObjectSampleImpl> objectSampleFactory = objInvokerFactoryGroup.getSomeSampleFactory(new ObjectInvokeFactory(), ObjectInvokeFactory.class);
        ObjectSampleImpl objectSampleImpl = objectSampleFactory.getSampleImpl();
        SampleEntity pojoSample = objectSampleImpl.getSample();
        objectSampleImpl.dosth(new SampleEntity().setAge(18).setName("YoYo").setGreek("yoyo").setSex(false));
        objectSampleImpl.dosth(new SampleEntity().setAge(28).setName("DaYoYo").setGreek("yoyoyoyo").setSex(false));


        System.out.println(integerInvokeFactoryGroup.equals(stringInvokeFactoryGroup)); //true

        IFactory<Animal> animalFactory = AbstractFactoryMethod.getSuitFactory("animal");
        ISuit<Animal> animal = animalFactory.getSuit(Animal.class);
        System.out.println(animal);
    }

    @Test
    public void testFactoryMethod() {
        System.out.println(FactoryMethod.genSuit("new").name());
        System.out.println(FactoryMethod.genSuit("x").name());
    }

    @Test
    public void testPrototype() {
        Prototype prototype = new Prototype();
        System.out.println(prototype);
        System.out.println(prototype.clone());
    }

    @After
    public void after() {
//        System.out.println(Sort.sortedArr);
//        System.out.println(Arrays.toString(Sort.testArr));
    }
}

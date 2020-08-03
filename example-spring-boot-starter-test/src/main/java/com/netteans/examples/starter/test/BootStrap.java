package com.netteans.examples.starter.test;

import com.netteans.example.starter.ExampleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SpringBootApplication
public class BootStrap {

    @Autowired(required = false)
    ExampleConfig exampleConfig;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    SpringApplicationRunListener applicationRunListener;

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }

    @Bean
    ApplicationRunner ar() {
        return args -> {
            IProxyTest o = (IProxyTest) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), IProxyTestImpl.class.getInterfaces(), new TestInvocationHandler(new IProxyTestImpl()));
            int s = o.vii("x");
            System.out.println(exampleConfig.toString());
        };
    }
}

@Component
class TestListener implements SpringApplicationRunListener {

    @Override
    public void starting() {
        System.out.println("starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("started");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("running");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("failed");
    }
}

class TestInvocationHandler implements InvocationHandler {

    private final IProxyTest proxy;

    public TestInvocationHandler(IProxyTest proxyTest) {
        this.proxy = proxyTest;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.proxy, args);
    }
}

interface IProxyTest {
    int vii(String r);
}

class IProxyTestImpl implements IProxyTest {

    @Override
    public int vii(String r) {
        return -1;
    }
}

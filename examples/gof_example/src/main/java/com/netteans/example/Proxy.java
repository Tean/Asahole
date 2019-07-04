package com.netteans.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Proxy {
    static class Static implements IProxy {
        private IProxy delegate;

        public void setDelegate(IProxy delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object proxyMethod() {
            System.out.println("invoke by static proxy");
            return delegate.proxyMethod();
        }
    }

    static class Dynamic {
        private Object delegate;

        public void setDelegate(Object delegate) {
            this.delegate = delegate;
        }

        public Object invoke(Method m) {
            System.out.println("invoke by dynamic proxy");
            try {
                return m.invoke(delegate);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

interface IProxy<R> {
    R proxyMethod();
}

class IProxyImpl implements IProxy<String> {

    private final String status;

    public IProxyImpl(String status) {
        this.status = status;
    }

    @Override
    public String proxyMethod() {
        return "proxy impl " + status;
    }
}

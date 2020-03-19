package com.netteans.examples.gof.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Proxy {
    public static class Static implements IProxy {
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

    public static class Dynamic {
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

    public static class JDKDynamicProxyFactory {
        public static <T> T newInstance(Class<T> interfas) {
            Object o = java.lang.reflect.Proxy.newProxyInstance(interfas.getClassLoader(), new Class[]{interfas}, new JDKInvocationHandlerImpl<T>(interfas));
            return interfas.cast(o);
        }

        public static class JDKInvocationHandlerImpl<T> implements InvocationHandler {
            private final Class<T> interfas;

            public JDKInvocationHandlerImpl(Class<T> interfas) {
                this.interfas = interfas;
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (Object.class.equals(method.getDeclaringClass())) {
                    System.out.println("JDKDynamicProxy default mothod: " + method);
                    return method.invoke(this, args);
                } else {
                    System.out.println("JDKDynamicProxy enhanced mothod: " + method);
                    System.out.println("Maybe you can invoke some other object's function");
                    Annotation[] interfasAnnotations = this.interfas.getAnnotations();
                    Annotation[] methodAnnotations = method.getAnnotations();

                    Annotation[] annotations = new Annotation[interfasAnnotations.length + methodAnnotations.length];
                    System.arraycopy(interfasAnnotations, 0, annotations, 0, interfasAnnotations.length);
                    System.arraycopy(methodAnnotations, 0, annotations, interfasAnnotations.length, methodAnnotations.length);

                    String msg = "bothering";
                    for (Annotation annotation : annotations) {
                        if (annotation instanceof Passion) {
                            Passion passion = (Passion) annotation;
                            boolean passed = passion.passed();
                            if (passed) {
                                msg = "have passtion";
                            }
                        }
                    }
                    return msg;
                }
            }
        }


    }
}


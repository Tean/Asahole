package com.netteans.examples.shiro.test;

import com.netteans.example.shiro.ILambda;
import com.netteans.example.shiro.VoidLambda;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.util.function.Consumer;

public class TestCase {

    @Test
    public void test() {
        ILambda<String, String> lambda = param -> "Return for: " + param;
        VLimpl vLimpl = new VLimpl();
        Runnable runnable = vLimpl::lambda;
        VoidLambda voidLambda = vLimpl::lambda;
        Runnable runnable1 = VLimpl::staticlambda;

        Runnable runnable2 = this::lambda;
        VoidLambda voidLambda1 = this::lambda;
        String a = "a";
        String b = "b";
        String ab = "a" + "b";
        String anb = a + b;
        String nab = new String("ab");
        String abi = "ab".intern();
        String anbi = anb.intern();

        Consumer consumer = System.out::println;
    }

    void lambda() {
    }

    @Test
    public void test2() {
        Runnable aNew = Vinit::new;
        aNew.run();

        Vinit vinit = new Vinit();
        Runnable vrun = vinit::vrun;
        vrun.run();
        VoidLambda voidLambda = Vinit::new;
        System.out.println(voidLambda.getClass());
        System.out.println(voidLambda instanceof Proxy);

        byte[] testCase$$Lambda$3s = ProxyGenerator.generateProxyClass("TestCase$$Lambda$3", new Class[]{});

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("./testCase$$Lambda$3s.class");
            outputStream.write(testCase$$Lambda$3s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Vinit {
    public Vinit() {
        System.out.println("vinit");
    }

    public void vrun() {
        System.out.println("vrun");
    }
}

class VLimpl implements VoidLambda {
    static void staticlambda() {
    }

    @Override
    public void lambda() {

    }
}

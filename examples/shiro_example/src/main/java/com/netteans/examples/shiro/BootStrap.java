package com.netteans.examples.shiro;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

@SpringBootApplication
//@ComponentScans({@ComponentScan("com.netteans.examples.shiro.controller")})
public class BootStrap {


    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }

    @Bean
    public ApplicationRunner ar() {
        Object waitobj = new Object();
        RoundSort rs = new RoundSort(123, 321, 1234567);
        System.out.println("ar");
        List<String> s = new LinkedList<String>();
//        AWatch aw = System.out::println;
//        AThrow a = () -> {
//            aw.fireExcept(new Exception("exept to aw"));
//        };
//        a.t();

        List<String> sls = new ArrayList<>();
        sls.add("abc");
        sls.add("bcd");
        sls.add("abd");
        sls.add("bad");
        sls.add("cad");
        sls.add("ada");
        AtomicBoolean out = new AtomicBoolean(false);
        long a = sls.stream().filter(s1 -> {
            System.out.println("exec " + s1);
            if (out.get()) return false;
            else if (!s1.startsWith("a")) {
                out.set(true);
                return false;
            }
            return true;
        }).count();
        boolean a1 = false;
        System.out.println(a1);
        return args -> {
            int[] arr = new int[]{1, 2, 3, 4, 5};
            for (int i : arr) {
                int finalI = i;
                new Thread(() -> {
                    synchronized (waitobj) {
                        System.out.println(Calendar.getInstance().getTime().toString() + " :" + finalI + " get waitobj");
                        try {
//                            TimeUnit.SECONDS.sleep(finalI + 5);
                            waitobj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Calendar.getInstance().getTime().toString() + " :" + finalI + " wakeup waitobj");
                    }
                    arr[i - 1] = 54;
                }).start();
            }
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Arrays.toString(arr));
            synchronized (waitobj) {
                waitobj.notifyAll();
            }
        };
    }

    private IPy p = new Zpy();
}

class Py implements IPy {
    static {
        System.out.println("init static py");
    }

    private p p = new p(1);

    public Py() {
        System.out.println("init py");
    }
}

class Zpy extends Py {
    static {
        System.out.println("init static zpy");
    }

    private p p = new p(2);

    public Zpy() {
        System.out.println("init zpy");
    }
}

class p {
    public p(int x) {
        System.out.println("init p " + x);
    }
}

interface AThrow<T, R> extends Function<T, R> {
    void t(Exception ex) throws Exception;
}

interface AWatch<T> {
    void fireExcept(Exception e);
}

class RoundSort {
    private final Round round;

    class Round {
        private final RNode[] slots;

        public Round() {
            slots = new RNode[16];
        }

        public void initSlots(int... values) {
            for (int value : values) {
                int smark = 0x8000;
                byte s1 = (byte) ((value & 0x8000) >>> 12);
            }
        }
    }

    private class RNode {
        private final byte mark;
        RNode next;

        public RNode(byte mark) {
            this.mark = mark;
        }
    }

    public RoundSort(int... values) {
        this.round = new Round();
        this.round.initSlots(values);
    }

    public int[] sort() {
        return sort(true);
    }

    public int[] sort(boolean lfst) {
        if (lfst) {

        }
        return new int[0];
    }
}
package com.netteans.examples.shiro.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestBootStrap {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext("com.netteans.examples.shiro.test");

        System.out.println(ac.getApplicationName());
    }
}

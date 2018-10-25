package com.netteans.test;

import com.netteans.controller.Controller;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DemoApplicationTests {

    private Controller controller;

    @Before
    public void before() {
        controller = new Controller();
    }

    @Test
    public void contextLoads() {
        controller.get(1);
//        controller.getByName("");
    }

    @After
    public void after() {

    }
}

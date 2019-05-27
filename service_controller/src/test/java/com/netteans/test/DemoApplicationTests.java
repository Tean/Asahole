package com.netteans.test;

import com.netteans.backend.controller.ExampleController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DemoApplicationTests {

    private ExampleController exampleController;

    @Before
    public void before() {
        exampleController = new ExampleController();
    }

    @Test
    public void contextLoads() {
        exampleController.get(1);
//        exampleController.getByName("");
    }

    @After
    public void after() {

    }
}

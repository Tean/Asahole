package com.netteans.examples.gof.test;

import com.netteans.examples.gof.sort.Sort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class TestCase {

    @Before
    public void before() {
        System.out.println(Sort.testArr);
    }

    @Test
    public void test() {
        Sort.sortedArr = Sort.bubble(Sort.testArr);
        Sort.sortedArr = Sort.selection(Sort.testArr);
    }

    @After
    public void after() {
        System.out.println(Sort.sortedArr);
        System.out.println(Arrays.toString(Sort.testArr));
    }
}

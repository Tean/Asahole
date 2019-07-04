package com.netteans.agent.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LeetCodeTestBootStrap {

    @Test
    public void testSolution() {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        new ThreadPoolExecutor(4, 4, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>()).execute(() -> {
            while (countDownLatch.getCount() > 0) {
                countDownLatch.countDown();
                System.out.println("count down left: " + countDownLatch.getCount());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            countDownLatch.await(5, TimeUnit.SECONDS);
            System.out.println("pass await");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] ins = {String.valueOf(1), String.valueOf(2), String.valueOf(3), String.valueOf(4), String.valueOf(5)};
        List l = Arrays.asList(new int[]{1, 2, 3, 4, 5});
        System.out.println(ins.getClass().getName());
        System.out.println(l.get(0).getClass().getName());
        System.out.println(l.size());
        String a = String.valueOf(new Random().nextInt());
        switch (a) {
            case "1":
                System.out.println(1);
            case "2":
                System.out.println(2);
            default:
                System.out.println(0);
        }
        try {
            countDownLatch.await();
            System.out.println("pass await again");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        new Solution().run("au");
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    int run(String s) {
        HashMap<Character, Integer> vm = new HashMap<>();
        int left = 0;
        int right = 0;
        int slength = Math.min(s.length(), 1);

        while (right < s.length()) {

            int nlen = right - left;

            slength = nlen > slength ? nlen : slength;

            if (s.charAt(left) == s.charAt(right) || vm.containsKey(s.charAt(right))) {
                left = right;
                vm.clear();
            }
            vm.put(s.charAt(right), right);

            right++;
        }

        return slength;
    }
}
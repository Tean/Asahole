package com.netteans.agent.test;

import org.junit.Test;

import java.util.HashMap;

public class LeetCodeTestBootStrap {

    @Test
    public void testSolution() {
        new Solution().run("au");
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
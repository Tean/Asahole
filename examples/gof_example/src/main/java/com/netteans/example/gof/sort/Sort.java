package com.netteans.example.gof.sort;

public class Sort {
    public static int[] testArr = new int[]{90, 74, 94, 12, 76, 4, 29, 85, 71, 67, 5, 96, 83, 42, 38, 19, 3, 45, 28, 53, 69, 98, 58, 10, 58, 60, 31, 43, 6, 54, 95, 13, 16, 99, 79, 86, 55, 44, 61, 12, 73, 43, 23, 93, 81, 75, 42, 23, 57, 14, 72, 70, 25, 83, 7, 78, 99, 46, 74, 93, 18, 62, 51, 79, 72, 52, 26, 65, 30, 94, 24, 74, 91, 2, 34, 81, 80, 99, 28, 43, 45, 69, 63, 21, 61, 41, 36, 33, 6, 99, 28, 12, 89, 6, 2, 8, 62, 67, 9, 29};
    public static int[] sortedArr;

    public static int[] bubble(int[] src) {
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src.length; j++) {
                if (src[i] < src[j]) {
                    int tmp = src[i];
                    src[i] = src[j];
                    src[j] = tmp;
                }
            }
        }
        return src;
    }

    public static int[] selection(int[] src) {
        for (int i = 0; i < src.length; i++) {
            int min = i;
            int curr = src[min];
            for (int j = i; j < src.length; j++) {
                if (src[j] < curr) {
                    curr = src[j];
                    min = j;
                }
            }
            int temp = src[i];
            src[i] = curr;
            src[min] = temp;
        }
        return src;
    }

    public static int[] insert(int[] src) {
        if (src.length == 0) return src;
        int[] sorted = new int[src.length];

        // 插入排序

        return sorted;
    }
}

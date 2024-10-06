package com.shy.code;

import org.junit.Test;

public class Code05Test {


    // 题目：数组a，长度为n，  0 < n <= 100000,  数组无序， 数组中元素都是0-100的整数。
    // 满足 0 <= i < j < n 且 a[i] + a[j] = 100，的i，j有多少种组合？
    @Test
    public void test02() {

        int[] a = {45, 13, 55, 78, 98, 22};

        System.out.println(getNum(a));
    }

    public int getNum(int[] arr) {


        int[][] dp = new int[arr.length][arr.length];
        return process(arr, 0, arr.length - 1, dp);


    }

    public int process(int[] arr, int i, int j, int[][] dp) {

        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        if (i == j) {
            return 0;
        }
        if (i < j && arr[i] + arr[j] == 100) {
            dp[i][j] = 1;
            return 1;
        }
        int n = process(arr, i + 1, j, dp) + process(arr, i, j - 1, dp);
        dp[i][j] = n;
        return n;
    }


}

package com.shy.code5;

import java.util.Objects;

/**
 * @author 石皓岩
 * @createDate 2020/9/27 11:11
 * 描述:判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
public class MainCode5 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(1221));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int resaveNum = 0;
        while (x > resaveNum) {
            resaveNum = resaveNum * 10 + x % 10;
            x /= 10;
        }
        return x == resaveNum || x == resaveNum / 10;
    }

    private static boolean isPalindrome1(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        String str = String.valueOf(x);
        for (int i = 0; i < str.length() / 2; i++) {
            String startNum = str.substring(i, i + 1);
            String endNum = str.substring(str.length() - 1 - i, str.length() - i);
            if (!Objects.equals(startNum, endNum)) {
                return false;
            }
        }
        return true;
    }
}

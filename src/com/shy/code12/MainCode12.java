package com.shy.code12;

/**
 * @author 石皓岩
 * @date 2020/10/1 20:03
 * 描述:
 */
public class MainCode12 {
    public static void main(String[] args) {
        strStr("a", "a");
    }

    public static int strStr(String haystack, String needle) {

        int length = needle.length();
        if (haystack.length() == 0 && length == 0) {
            return 0;
        }
        if (length > haystack.length() || haystack.length() == 0) {
            return -1;
        }
        for (int i = 0; i <= haystack.length() - length; i++) {
            if (haystack.substring(i, i + length).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}

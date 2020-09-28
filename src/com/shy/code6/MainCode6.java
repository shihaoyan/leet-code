package com.shy.code6;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 石皓岩
 * @date 2020/9/27 14:36
 * 描述:
 */
public class MainCode6 {
    public static void main(String[] args) {
        System.out.println(romanToInt("IV"));
    }


    public static int romanToInt(String s) {

        // 把字符放入缓存
        Map<Character, Integer> map = new HashMap<>(7);
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        // 首先应该把字符串转为字符数组
        char[] chars = s.toCharArray();
        // 开始处理数组
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            Integer value = map.get(chars[i]);
            result += value;
            if (i + 1 < chars.length) {
                if (chars[i] == 'I') {
                    if (chars[i + 1] == 'V' || chars[i + 1] == 'X') {
                        result -= 2;
                    }
                } else if (chars[i] == 'X') {
                    if (chars[i + 1] == 'L' || chars[i + 1] == 'C') {
                        result -= 20;
                    }
                } else if (chars[i] == 'C') {
                    if (chars[i + 1] == 'D' || chars[i + 1] == 'M') {
                        result -= 200;
                    }
                }
            }
        }
        return result;
    }

}

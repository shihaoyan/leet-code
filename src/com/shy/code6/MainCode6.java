package com.shy.code6;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 石皓岩
 * @date 2020/9/27 14:36
 * 描述:
 */
public class MainCode6 {


    public int romanToInt(String s) {

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
        for (int i = 0; i < chars.length; i++) {

        }


        return 0;
    }

}

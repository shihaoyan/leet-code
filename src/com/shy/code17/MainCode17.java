package com.shy.code17;

import java.util.*;

/**
 * @author 石皓岩
 * @date 2020/12/14 6:54
 * 描述:字母异位词分组
 */
public class MainCode17 {
    public static void main(String[] args) {
        groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
    }


    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            // 这一步就是把之前存在的数组拿出来，如果之前没有集合，就创建一个集合
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

}

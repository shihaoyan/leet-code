package com.shy.code8;

import java.util.*;

/**
 * @author 石皓岩
 * @date 2020/9/28 20:42
 * 描述:
 */
public class MainCode8 {

    public static void main(String[] args) {

        // System.out.println(isValid("({{(){}[]}})"));
        MainCode8 mainCode8 = new MainCode8();
        List<String> abc = mainCode8.permutation("abc");
        // 遍历打印abc
        for (String s : abc) {
            System.out.println(s);
        }
    }

    public static boolean isValid(String s) {

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        Stack<Character> stack = new Stack<>();
        if (s.isEmpty()) {
            return true;
        }
        if (s.length() % 2 != 0) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (stack.isEmpty() || !stack.peek().equals(map.get(c))) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public List<String> permutation(String str) {
        // 字符串为空判断
        if (str == null || str.isEmpty()) {
            return Collections.emptyList();
        }
        // 把字符串转换为字符串组并放入到List里面
        char[] chars = str.toCharArray();
        List<Character> list = new ArrayList<>();
        for (char aChar : chars) {
            list.add(aChar);
        }
        String path = "";
        List<String> ans = new ArrayList<>();
        process(list, path, ans);
        return ans;
    }

    /**
     * 对字符列表进行处理，生成所有可能的排列组合路径
     *
     * @param list 待处理的字符列表
     * @param path 当前构建的路径
     * @param ans  存储所有可能的路径的结果列表
     */
    private void process(List<Character> list, String path, List<String> ans) {
        // 当字符列表为空时，说明已经处理完毕，将当前路径添加到结果列表中
        if (list.isEmpty()) {
            ans.add(path);
        } else {
            // 遍历字符列表，对每个字符进行处理
            for (int i = 0; i < list.size(); i++) {
                Character c = list.get(i);
                // 移除当前字符，以便在新的路径中使用
                Character cur = list.remove(i);
                // 递归调用process，继续构建新的路径
                process(list, path + cur, ans);
                // 将当前字符重新添加到列表中，以便下一次循环使用
                list.add(i, cur);
            }
        }
    }


}

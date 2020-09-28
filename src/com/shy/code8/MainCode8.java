package com.shy.code8;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author 石皓岩
 * @date 2020/9/28 20:42
 * 描述:
 */
public class MainCode8 {
    public static void main(String[] args) {
        System.out.println(isValid("({{(){}[]}})"));
    }

    public static boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        Stack<Character> stack = new Stack<>();
        if (s.length() == 0) {
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
}

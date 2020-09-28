package com.shy.code7;

/**
 * @author haoyan.shi
 */
public class MainCode7 {
    /**
     * 最长公共前缀
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    public static String longestCommonPrefix(String[] strs) {

        if (strs.length == 0) {
            return "";
        }
        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < ans.length() && j < strs[i].length(); j++) {
                if (ans.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
                ans = ans.substring(0, j);
                if ("".equals(ans)) {
                    return ans;
                }
            }
        }
        return ans;

    }

}

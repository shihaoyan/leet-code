package com.shy.code;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Code01Test {

    @Test
    public void test01() {

        System.out.println(charReverse("123214") == charReverse2("123214"));
    }

    /**
     * 计算字符串中字符的逆序对数量
     *
     * @param s 输入的字符串，仅包含数字字符
     * @return 返回字符串可以被解析的方式数量
     */
    private int charReverse(String s) {

        return charReverse(s.toCharArray(), 0);
    }

    /**
     * 计算字符串中字符的反转方式数量
     * 英文字符可以通过单个字符或者两个字符一起反转得到
     *
     * @param chars 字符数组，代表待反转的字符串
     * @param index 当前处理的字符索引
     * @return 返回从当前位置到末尾的字符反转方式数量
     */
    private int charReverse(char[] chars, int index) {
        // 如果索引等于数组长度，说明已经处理完毕，返回1表示一种反转方式
        if (index == chars.length) {
            return 1;
        }
        // 如果当前位置是0字符，说明前面转错了
        if (chars[index] == '0') {
            return 0;
        }
        // 两种情况 1. 当前位置自己转换
        int p1 = charReverse(chars, index + 1);
        // 第二种情况，当前位置+当前位置+1位置进行转换 当时需要保证当前位置+1 小于最大长度
        int p2 = 0;
        // 如果当前位置+1 小于最大长度，并且两个字符组成的数字小于等于26，则可以作为两个字符一起反转
        if (index + 1 < chars.length && (chars[index] - '0') * 10 + (chars[index + 1] - '0') <= 26) {
            // 如果当前位置+1 小于最大长度，则进行转换
            p2 = charReverse(chars, index + 2);
        }
        // 返回两种反转方式的总和
        return p1 + p2;
    }


    /**
     * 计算字符串中字符的逆序对数量（方法2）
     * 该方法通过动态规划的方式计算字符串可以被解析的方式数量
     *
     * @param s 输入的字符串，仅包含数字字符
     * @return 返回字符串可以被解析的方式数量
     */
    private int charReverse2(String s) {
        // 初始化动态规划数组，用于存储每个字符位置的解析方式数量
        int[] dp = new int[s.length() + 1];
        // dp表最后一个位置是1，表示字符串最后一个字符有一种解析方式
        dp[s.length()] = 1;
        // 从字符串末尾开始遍历，计算每个字符位置的解析方式数量
        for (int index = s.length() - 1; index >= 0; index--) {
            // 获取当前字符后续字符的解析方式数量
            int p1 = dp[index + 1];
            // 检查当前字符是否可以与后续字符组成有效的数字（1到26之间）
            if (s.charAt(index) != '0') {
                int p2 = 0;
                // 如果当前字符和后续字符组成的数字在1到26之间，则计算该组合的解析方式数量
                if (index + 1 < s.length() && (s.charAt(index) - '0') * 10 + (s.charAt(index + 1) - '0') <= 26) {
                    p2 = dp[index + 2];
                }
                // 当前字符位置的解析方式数量是后续字符的解析方式数量加上组合的解析方式数量
                dp[index] = p1 + p2;
            }
        }
        // 返回字符串整体的解析方式数量
        return dp[0];
    }

    @Test
    public void test02() {

        List<List<Integer>> permute = permute(new int[]{1, 2, 3});
        System.out.println(permute);
    }

    public List<List<Integer>> permute(int[] rest) {

        List<List<Integer>> ans = new ArrayList<>();
        f(rest, new ArrayList<>(), ans, new boolean[rest.length]);
        return ans;
    }

    /**
     * 递归函数，用于生成所有可能的排列组合
     * 该函数通过不断缩小问题规模，将一个大问题分解为多个小问题来求解
     * 当问题规模缩小时，将当前的路径加入到答案列表中
     *
     * @param rest 剩余未选择的元素列表
     * @param path 当前已经选择的元素路径
     * @param ans  存储所有可能的排列组合的答案列表
     */
    public void f(int[] rest, List<Integer> path, List<List<Integer>> ans, boolean[] used) {
        // 如果剩余元素列表为空，说明当前路径已经是一个完整的排列，将其添加到答案列表中
        if (rest.length == path.size()) {
            ans.add(new ArrayList<>(path));
        } else {
            // 遍历剩余的每个元素，尝试将其加入当前路径
            for (int i = 0; i < rest.length; i++) {
                if (!used[i]) {
                    path.add(rest[i]);
                    used[i] = true;
                    f(rest, path, ans, used);
                    path.remove(path.size() - 1);
                    used[i] = false;
                }
            }
        }
    }

    @Test
    public void test03() {

        List<List<Integer>> ans = new ArrayList<>();
        // 调用f1方法
        f2(new int[]{1, 2, 3}, new ArrayList<>(), 0, ans);
        System.out.println(ans);
        Set<Integer> set = new HashSet<>();
        new ArrayList<>(set);

    }

    public void f1(int[] num, List<Integer> path, List<List<Integer>> ans) {
        // 求numbs全部子序列
        if (num.length == path.size()) {
            ans.add(new ArrayList<>(path));
        } else {
            for (int j : num) {
                if (!path.contains(j)) {
                    path.add(j);
                    f1(num, path, ans);
                    path.remove(path.size() - 1);
                }
            }
        }

    }

    // 求数组全部子序列
    public void f2(int[] numbs, List<Integer> path, int index, List<List<Integer>> ans) {
        // 求numbs全部子序列
        if (index == numbs.length) {
            ans.add(new ArrayList<>(path));
        } else {
            for (int i = index; i < numbs.length; i++) {
                if (!path.contains(numbs[i]) && !ans.contains(path)) {
                    // 两种可能
                    // 1. 选择了numbs[i]
                    path.add(numbs[i]);
                    f2(numbs, path, i + 1, ans);
                    path.remove(path.size() - 1);
                    // 2. 没有选择numbs[i]
                    f2(numbs, path, i + 1, ans);
                }
            }
        }

    }

    @Test
    public void test04() {

        List<Integer> integers = spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}});
        System.out.println(integers);
    }

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> ans = new ArrayList<>();
        f(matrix, 0, 0, ans);
        return ans;
    }

    public void f(int[][] matrix, int row, int col, List<Integer> ans) {

        if (row >= matrix.length || row < 0 || col >= matrix[0].length || col < 0 || ans.contains(matrix[row][col])) {
            return;
        }
        ans.add(matrix[row][col]);

        // 往上的位置需要走完
        if ((row - 1) < 0 || ans.contains(matrix[row - 1][col])) {
            // 往右走到头
            f(matrix, row, col + 1, ans);
        }
        // 往右的位置走完
        if ((col + 1) >= matrix[0].length || ans.contains(matrix[row][col + 1])) {
            // 往下走到头
            f(matrix, row + 1, col, ans);
        }
        // 往下的位置走完
        if ((row + 1) >= matrix.length || ans.contains(matrix[row + 1][col])) {
            // 往左走到头
            f(matrix, row, col - 1, ans);
        }
        // 往左的位置走完
        if ((col - 1) < 0 || ans.contains(matrix[row][col - 1])) {
            // 往上走到头
            f(matrix, row - 1, col, ans);
        }
    }

    @Test
    public void test05() {

        List<Integer> integers = spiralOrder1(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}});
        System.out.println(integers);
    }

    public List<Integer> spiralOrder1(int[][] matrix) {

        List<Integer> ans = new ArrayList<>();
        int rowMax = matrix.length;
        int colMax = matrix[0].length;
        // 定义一个指针
        int row = 0;
        int col = 0;
        while (row >= 0 && row < rowMax && col >= 0 && col < colMax) {
            ans.add(matrix[row][col]);
            if (ans.size() >= rowMax * colMax) {
                break;
            }
            // 先往右走到头
            if (col + 1 < colMax) {
                col += 1;
                continue;
            }
            // 接下来往下走到头
            if (row + 1 < rowMax) {
                row += 1;
                continue;
            }
            // 接下来往左走到头
            if (col - 1 >= 0) {
                col -= 1;
                continue;
            }
            // 接下来往上走到头
            if (row - 1 >= 0) {
                row -= 1;
                continue;
            }


        }


        return ans;
    }

    // List集合转为数组
    public int[] listToArray(List<Integer> list) {
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }


}

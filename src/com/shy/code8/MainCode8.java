package com.shy.code8;

import java.util.*;

/**
 * @author 石皓岩
 * @date 2020/9/28 20:42
 * 描述:
 */
public class MainCode8 {

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

    public List<String> permutation1(String str) {

        if (str == null || str.isEmpty()) {
            return Collections.emptyList();
        }

        char[] chars = str.toCharArray();
        List<String> ans = new ArrayList<>();
        process(chars, 0, ans);
        return ans;
    }

    private void process(char[] chars, int index, List<String> ans) {

        if (index == chars.length) {
            ans.add(new String(chars));
        } else {
            boolean[] valid = new boolean[256];
            for (int i = index; i < chars.length; i++) {
                if (!valid[chars[i]]) {
                    valid[chars[i]] = true;
                    swap(chars, index, i);
                    process(chars, index + 1, ans);
                    swap(chars, index, i);
                }
            }
        }
    }

    private void swap(char[] chars, int index, int i) {
        // 对于chars数组，交换 index,i的数据
        char temp = chars[index];
        chars[index] = chars[i];
        chars[i] = temp;
    }


    public void reverse(Stack<Integer> stack) {

        if (stack.isEmpty()) {
            return;
        }
        // 先栈顶出栈
        int res = reverseStack(stack);
        // 递归调用
        reverse(stack);
        // 栈顶出栈后，将栈顶元素入栈
        stack.push(res);
    }

    public int reverseStack(Stack<Integer> stack) {
        // 先栈顶出站
        int res = stack.pop();
        if (stack.isEmpty()) {
            return res;
        } else {
            // 我需要递归去获取最后一个值
            int last = reverseStack(stack);
            stack.push(res);
            return last;
        }
    }

    /**
     * 计算从当前位置到达目标位置的路径数量
     *
     * @param N    楼层数，表示有多少个位置
     * @param aim  目标位置
     * @param rest 剩余步数，表示还可以移动多少次
     * @param cur  当前位置
     * @return 到达目标位置的路径数量
     */
    public int robit(int N, int aim, int rest, int cur) {

        // 当剩余步数为0时，判断当前位置是否为目标位置
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }

        // 当前位置为1，只能向右移动
        if (cur == 1) {
            return robit(N, aim, rest - 1, 2);
        }

        // 当前位置为N，只能向左移动
        if (cur == N) {
            return robit(N, aim, rest - 1, N - 1);
        }

        // 如果在中间位置，可以向左或向右移动
        return robit(N, aim, rest - 1, cur + 1) + robit(N, aim, rest - 1, cur - 1);

    }

    /**
     * 功能：计算从当前位置到达目标位置的方法数
     * 说明：使用动态规划和递归方式实现
     *
     * @param cur   当前所在位置
     * @param aim   目标位置
     * @param reset 剩余步数
     * @param N     总共的位置数
     * @param dp    动态规划数组，存储已计算过的结果
     * @return 到达目标位置的方法数
     */
    public int robit1(int cur, int aim, int reset, int N, int[][] dp) {

        // 检查是否已计算过当前结果，如果是直接返回
        if (dp[cur][reset] != -1) {
            return dp[cur][reset];
        }
        if (reset == 0) {
            return cur == aim ? 1 : 0;
        }
        // 初始化答案
        int ans = 0;
        // 根据当前位置进行条件分支
        if (cur == 1) {
            // 如果在第一个位置，只能向右走
            ans = robit1(2, aim, reset - 1, N, dp);
        } else if (cur == N) {
            // 如果在最后一个位置，只能向左走
            ans = robit1(cur - 1, aim, reset - 1, N, dp);
        } else {
            // 如果在中间位置，可以向左或向右走
            ans = robit1(cur + 1, aim, reset - 1, N, dp) + robit1(cur - 1, aim, reset - 1, N, dp);
        }
        // 将结果存储到动态规划数组中
        dp[cur][reset] = ans;
        // 返回结果
        return ans;

    }


    /**
     * 计算在特定规则下的移动路径数量
     *
     * @param cur   当前位置
     * @param aim   目标位置
     * @param reset 移动步数
     * @param N     环形路径上的位置数量
     * @return 从当前位置到达目标位置的路径数量
     */
    public int robit3(int cur, int aim, int reset, int N) {

        // 初始化动态规划数组，默认所有位置都是零
        int[][] dp = new int[N + 1][reset + 1];
        // 设定目标位置在第0步时的值为1，表示从该位置开始
        dp[aim][0] = 1;

        // 动态规划填表
        for (int i = 1; i <= reset; i++) { // 遍历每一步
            // 处理边界情况，当在第一个位置时，只能向右移动
            dp[1][i] = dp[2][i - 1];
            for (int j = 2; j < N; j++) {
                // 更新中间位置的路径数量，来源于前一步和后一步路径数量之和
                dp[j][i] = dp[j - 1][i - 1] + dp[j + 1][i - 1];
            }
            // 处理边界情况，当在最后一个位置时，只能向左移动
            dp[N][i] = dp[N - 1][i - 1];
        }
        // 返回从当前位置到目标位置的路径数量
        return dp[cur][reset];
    }

    /**
     * 计算在给定数组中，先手和后手分别能拿到的最大值中的较大值
     *
     * @param arr 整数数组，表示游戏中的分数
     * @return 先手和后手能拿到的最大值中的较大值
     */
    public int getFGMax(int[] arr) {
        // 先手拿的最大值
        int p1 = f(arr, 0, arr.length - 1);
        // 后手拿的最大值
        int p2 = g(arr, 0, arr.length - 1);
        // 返回较大值
        return Math.max(p1, p2);
    }


    /**
     * 计算先手在给定数组arr中可以拿到的最大值
     *
     * @param arr 整数数组，代表每张牌的分数
     * @param L   当前考虑的左边界
     * @param R   当前考虑的右边界
     * @return 先手能拿到的最大分数
     */
    private int f(int[] arr, int L, int R) {
        // 这种情况说明，只剩一张牌，那先手直接拿走
        if (L == R) {
            return arr[L];
        }
        // 如果先手拿arr[L]， 那还得加上后手拿到的排
        int p1 = arr[L] + g(arr, L + 1, R);
        // 如果先手拿arr[R]，那还得加上后手拿到的牌
        int p2 = arr[R] + g(arr, L, R - 1);
        // 返回先手拿到的最大值
        return Math.max(p1, p2);
    }


    /**
     * 计算在数组arr中，从索引L到R的范围内，后手玩家的最优策略得分
     * 该函数用于解决两个玩家轮流从数组中取数的问题，其中每个玩家都试图最大化自己得分，同时最小化对手得分
     *
     * @param arr 整数数组，表示每个数字的分数
     * @param L   索引范围的起始位置
     * @param R   索引范围的结束位置
     * @return 后手玩家在最优策略下的得分
     */
    private int g(int[] arr, int L, int R) {
        // 如果L == R，表示只有一个数字可取，后手玩家无法得分，返回0
        if (L == R) {
            return 0;
        }
        // 计算如果先手玩家从索引L取数，后手玩家的最优得分
        int p1 = f(arr, L + 1, R);
        // 计算如果先手玩家从索引R取数，后手玩家的最优得分
        int p2 = f(arr, L, R - 1);
        // 先手玩家会选择使后手玩家得分最小的策略，返回p1和p2中的较小值
        return Math.min(p1, p2);
    }

    /**
     * 计算在给定数组中，先手和后手分别能拿到的最大值中的较大值
     *
     * @param arr 整数数组，表示游戏中的分数
     * @return 先手和后手能拿到的最大值中的较大值
     */
    public int getFGMax1(int[] arr) {

        int[][] fdp = new int[arr.length][arr.length];
        int[][] gdp = new int[arr.length][arr.length];
        Arrays.stream(fdp).forEach(a -> Arrays.fill(a, -1));
        Arrays.stream(gdp).forEach(a -> Arrays.fill(a, -1));
        // 先手拿的最大值
        int p1 = f1(arr, 0, arr.length - 1, fdp, gdp);
        // 后手拿的最大值
        int p2 = g1(arr, 0, arr.length - 1, fdp, gdp);
        // 返回较大值
        return Math.max(p1, p2);
    }

    /**
     * 计算先手在给定数组arr中可以拿到的最大值
     *
     * @param arr 整数数组，代表每张牌的分数
     * @param L   当前考虑的左边界
     * @param R   当前考虑的右边界
     * @return 先手能拿到的最大分数
     */
    private int f1(int[] arr, int L, int R, int[][] fdp, int[][] gdp) {

        if (fdp[L][R] != -1) {
            return fdp[L][R];
        }
        // 这种情况说明，只剩一张牌，那先手直接拿走
        int max = 0;
        if (L == R) {
            max = arr[L];
        } else {
            // 如果先手拿arr[L]， 那还得加上后手拿到的排
            int p1 = arr[L] + g1(arr, L + 1, R, fdp, gdp);
            // 如果先手拿arr[R]，那还得加上后手拿到的牌
            int p2 = arr[R] + g1(arr, L, R - 1, fdp, gdp);
            // 返回先手拿到的最大值
            max = Math.max(p1, p2);
        }
        fdp[L][R] = max;
        return max;
    }


    /**
     * 计算在数组arr中，从索引L到R的范围内，后手玩家的最优策略得分
     * 该函数用于解决两个玩家轮流从数组中取数的问题，其中每个玩家都试图最大化自己得分，同时最小化对手得分
     *
     * @param arr 整数数组，表示每个数字的分数
     * @param L   索引范围的起始位置
     * @param R   索引范围的结束位置
     * @return 后手玩家在最优策略下的得分
     */
    private int g1(int[] arr, int L, int R, int[][] fdp, int[][] gdp) {

        if (gdp[L][R] != -1) {
            return gdp[L][R];
        }
        // 如果L == R，表示只有一个数字可取，后手玩家无法得分，返回0
        int min = 0;
        if (L != R) {
            // 计算如果先手玩家从索引L取数，后手玩家的最优得分
            int p1 = f1(arr, L + 1, R, fdp, gdp);
            // 计算如果先手玩家从索引R取数，后手玩家的最优得分
            int p2 = f1(arr, L, R - 1, fdp, gdp);
            min = Math.min(p1, p2);
        }
        gdp[L][R] = min;
        // 先手玩家会选择使后手玩家得分最小的策略，返回p1和p2中的较小值
        return min;
    }

    // 移除下面注释


    static class TreeNode {

        int val;

        TreeNode left;

        TreeNode right;

        TreeNode() {

        }

        TreeNode(int val) {

            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {

            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    public static void main(String[] args) {

        Solution1 solution = new Solution1();
        int i = solution.climbStairs(6);
        System.out.println(i);
    }

    static class Info {

        public int dis;

        public int maxDis;

        public Info(int dis) {

            this.dis = dis;
        }

    }

    static class Solution {

        public int maxPathSum(TreeNode root) {

            Info info = process(root);
            return info.dis;

        }

        public Info process(TreeNode root) {

            if (root == null) {
                return null;
            }
            Info leftV = process(root.left);
            Info rightV = process(root.right);

            return new Info(Math.max(leftV.dis, rightV.dis) + root.val);
        }

    }

    public int longestSubstring(String s, int k) {

        int n = s.length();
        return dfs(s, 0, n - 1, k);
    }

    /**
     * 使用深度优先搜索（DFS）策略来解决字符串分割问题，目标是找到可以被分割成的最长子字符串，使得每个字符出现的次数不少于k次
     *
     * @param s 输入的字符串
     * @param l 当前处理的子字符串的左边界
     * @param r 当前处理的子字符串的右边界
     * @param k 每个字符出现的最少次数
     * @return 最长子字符串的长度
     */
    public int dfs(String s, int l, int r, int k) {

        // 记录每个字符出现的次数
        int[] cnt = new int[26];
        // 统计子字符串中每个字符出现的次数
        for (int i = l; i <= r; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        // 尝试找到一个字符，其出现次数大于0且小于k，作为分割点
        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        // 如果没有需要分割的字符，说明当前子字符串满足条件，返回其长度
        if (split == 0) {
            return r - l + 1;
        }

        // 初始化当前遍历的位置
        int i = l;
        // 用于记录最长子字符串的长度
        int ret = 0;
        // 遍历子字符串，寻找满足条件的最长子字符串
        while (i <= r) {
            // 跳过所有需要分割的字符
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            // 如果已经遍历到末尾，跳出循环
            if (i > r) {
                break;
            }
            // 记录当前非分割字符的起始位置
            int start = i;
            // 跳过所有非分割字符
            while (i <= r && s.charAt(i) != split) {
                i++;
            }

            // 递归计算从start到i-1的子字符串中满足条件的最长子字符串长度
            int length = dfs(s, start, i - 1, k);
            // 更新最长子字符串的长度
            ret = Math.max(ret, length);
        }
        // 返回最长子字符串的长度
        return ret;
    }

    static class Solution1 {

        public int climbStairs(int n) {

            int[] dp = new int[n + 1];
            Arrays.fill(dp, -1);
            return process(0, n, dp);
        }


        public int process(int cur, int n, int[] dp) {

            if (cur > n) {
                return 0;
            }
            if (dp[cur] != -1) {
                return dp[cur];
            }
            // 当cur位置等于n说明爬到了
            int ans = 0;
            if (cur == n) {
                ans = 1;
                dp[cur] = 1;
                return 1;
            }
            ans = process(cur + 1, n, dp) + process(cur + 2, n, dp);
            dp[cur] = ans;
            return ans;


        }

    }

    /**
     * 当前考虑到了index号货物，后面的货物可以自由选择.
     *
     * @param w
     * @param v
     * @param index
     * @param bag
     * @return
     */
    public int process(int[] w, int[] v, int index, int bag) {

        if (bag < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        // 一共两种选择，1. 选择index位置的货物，2. 不选择index位置的货物
        int p1 = process(w, v, index + 1, bag);
        int p2 = process(w, v, index + 1, bag - w[index]);
        if (p2 != -1) {
            // 计算价值
            p2 = v[index] + p2;
        }
        return Math.max(p1, p2);
    }

    public int process2(int[] w, int[] v, int index, int bag) {

        int[][] dp = new int[w.length + 1][bag + 1];
        // 这个dp表，最后一行是零，不需要单独赋值，
        // 按照递归，普遍行依赖于index+1位置，所以只需要从希望上推到0位置即可
        for (int i = w.length - 1; i >= 0; i--) {
            // 列是无所谓的，直接从后往前
            for (int j = 0; j <= bag; j++) {
                int p1 = dp[i + 1][j];
                int p2 = 0;
                int next = bag - w[i] < 0 ? -1 : dp[i + 1][bag - w[i]];
                if (next != -1) {
                    p2 = v[i] + next;
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public int process(char[] chars, int index) {

        if (index == chars.length) {
            return 1;
        }
        if (chars[index] == '0') {
            // 这种情况说明 index前面转错了
            return 0;
        }
        // 其他普遍位置，只有两种情况，1. 当前位置直接转换 2. 当前位置加上下一个位置转换
        int p1 = process(chars, index + 1);
        int p2 = 0;
        // 当前位置+下一个位置转换有前提条件，1. index+1 需要小于数组长度， 2. 两个数的和不能大于27
        if (index + 1 < chars.length && (chars[index] - '0') * 10 + (chars[index + 1] - '0') <= 26) {
            p2 = process(chars, index + 2);
        }
        return p1 + p2;
    }

    public int process1(char[] chars, int index) {

        int[] dp = new int[chars.length + 1];

        dp[chars.length] = 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            int p1 = dp[i + 1];
            int p2 = 0;
            if (chars[i] != '0') {
                p2 = dp[i + 1];
                if (i + 1 < chars.length && (chars[i] - '0') * 10 + (chars[i + 1] - '0') <= 26) {
                    p2 += dp[i + 2];
                }
                dp[i] = p1 + p2;
            }
        }
        return dp[0];
    }



}
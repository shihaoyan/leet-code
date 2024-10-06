package com.shy.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: shihaoyan
 * @date: 2024/9/19 22:51
 * @description:
 */
public class Code11Test {

    /**
     * 数组中是1-7的任意数字，随机组合排列，1-7分别代表星期一到星期日。
     * 要求：如果日期是连续的，则输出xx至yy生效，否则输出 xx/yy/zz生效
     * eg.[1,3,2,4,1] 输出【星期一至星期四生效】
     * eg.[1,3,5] 输出【星期一/星期三/星期五生效】
     *
     * @param args
     */

    public static final String[] WEEK_ARRAY = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};

    public static final Map<Integer, String> MAP = new HashMap<>(7);

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        // 把日期存到map里面
        for (int i = 0; i < WEEK_ARRAY.length; i++) {
            MAP.put(i + 1, WEEK_ARRAY[i]);
        }
        int[] arr = new int[]{1, 2, 3, 4, 5};
        System.out.println(getWeek(arr));
    }

    public static String getWeek(int[] arr) {

        // 先对数组进行排序
        Arrays.sort(arr);
        // 从头遍历到尾，如果存在不连续的，跳出循环，然后从头遍历，每个日期单独数据
        int one = arr[0];
        boolean flag = true;
        for (int i = 1; i < arr.length; i++) {
            if (one != arr[i] && one + 1 != arr[i]) {
                flag = false;
                break;
            }
            one = arr[i];
        }
        // 如果flag == true ，直接拿头一个和最后一个组合
        if (flag) {
            // 直接输出结果.
            return MAP.get(arr[0]) + "到" + MAP.get(arr[arr.length - 1]) + "生效";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int j : arr) {
                sb.append(MAP.get(j)).append("/");
            }
            String res = sb.substring(0, sb.length() - 1);
            return res + "生效";
        }

    }


    static class Info {

        int max;

        int min;

        boolean ok;

        // 生成构造方法
        public Info(int max, int min, boolean ok) {
            this.max = max;
            this.min = min;
            this.ok = ok;
        }


    }

    public boolean isBST(TreeNode root) {
        return process(root).ok;
    }

    @Test
    public void test1() {
        TreeNode node1 = new TreeNode(2, null, null);
        TreeNode node2 = new TreeNode(2, null, null);
        TreeNode node3 = new TreeNode(2, null, null);
        node1.left = node2;
        node1.right = node3;
        System.out.println(isBST(node1));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class TreeNode {

        int val;

        TreeNode left;

        TreeNode right;

    }

    public Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info leftV = process(root.left);
        Info rightV = process(root.right);

        // 判断二叉树是否是二叉搜索树
        boolean ok = true;
        if (leftV != null && !leftV.ok) {
            ok = false;
        } else if (rightV != null && !rightV.ok) {
            ok = false;
        } else if (leftV != null && !(leftV.max < root.val)) {
            ok = false;
        } else if (rightV != null && !(rightV.min > root.val)) {
            ok = false;
        }
        int myMax = Integer.MIN_VALUE;
        int myMin = Integer.MAX_VALUE;
        if (leftV != null && rightV != null) {
            myMax = Math.max(root.val, leftV.max);
            myMax = Math.max(myMax, rightV.max);
            myMin = Math.min(root.val, leftV.min);
            myMin = Math.min(myMin, rightV.min);
        } else if (leftV != null && rightV == null) {
            myMax = Math.max(myMax, leftV.max);
            myMin = Math.min(myMin, leftV.min);
        } else if (leftV == null && rightV != null) {
            myMax = Math.max(myMax, rightV.max);
            myMin = Math.min(myMin, rightV.min);
        }
        return new Info(myMax, myMin, ok);

    }



}

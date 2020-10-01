package com.shy.code11;

/**
 * @author 石皓岩
 * @date 2020/10/1 13:26
 * 描述:移除元素
 */
public class MainCode11 {
    public static void main(String[] args) {
        System.out.println(removeElement(new int[]{4, 5}, 5));
    }

    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int p = 0;
        int q = nums.length - 1;
        while (p < q) {
            if (nums[p] != val) {
                p++;
            }
            if (nums[q] == val) {
                q--;
            }
            if (nums[p] == val && nums[q] != val && p < q) {
                nums[p] = nums[q];
                p++;
                q--;
            }
        }
        if (p == q) {
            return nums[p] != val ? ++p : p;
        }
        return p;
    }

}

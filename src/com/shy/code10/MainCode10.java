package com.shy.code10;

/**
 * @author haoyan.shi
 * 描述：
 * 删除排序数组中的重复项
 */
public class MainCode10 {
    public static void main(String[] args) {

    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}

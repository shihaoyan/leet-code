package com.shy.code13;

/**
 * @author 石皓岩
 * @date 2020/10/1 20:29
 * 描述:搜索插入位置
 */
public class MainCode13 {
    public static void main(String[] args) {

    }

    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private static int getIndex(int[] nums, int target) {
        if (target < nums[0]) {
            return 0;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            } else if (nums[i + 1] > target) {
                return i + 1;
            }
        }
        return 0;
    }

}

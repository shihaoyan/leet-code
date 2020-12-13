package com.shy.code16;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 石皓岩
 * @date 2020/12/13 8:47
 * 描述:存在重复元素
 */
public class MainCode16 {
    public static void main(String[] args) {

    }

    /**
     * 解题思路，最简单的方法就是通过hash来做，尤其是Java中的hash集合来做。
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            set.add(num);
        }
        if (nums.length > set.size()) {
            return true;
        }
        return false;
    }
    /**
     * 另一种方法是先对整合数组进行排序然后判断是否存在相邻的元素就行了
     * 但是我这里就不进行排序了，我写一下jdk1.8的stream流
     */
    public boolean containsDuplicate2(int[] nums) {
        return Arrays.stream(nums).distinct().count() < nums.length;
    }
}

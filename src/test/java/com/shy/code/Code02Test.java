package com.shy.code;

import org.junit.Test;

import java.util.*;

public class Code02Test {


    @Test
    public void test() {

        int[] numbs = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(numbs));
    }

    public List<List<Integer>> threeSum(int[] nums) {

        int[] numbs = new int[nums.length];
        Arrays.fill(numbs, 1);
        Set<List<Integer>> ans = new HashSet<>();
        f(numbs, 0, new ArrayList<>(), ans, nums, 0);
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (List<Integer> list : ans) {
            List<Integer> newList = new ArrayList<>();
            for (Integer n : list) {
                newList.add(nums[n]);
                set.add(n);
            }
            // 这组数据不能和上组数据用的数据一样
            if(!same(newList, set)){
                res.add(newList);
            }
        }
        return new ArrayList<>(ans);
    }

    private boolean same(List<Integer> newList, Set<Integer> set) {

        for (Integer n : newList) {
            if(set.contains(n)){
                set.remove(n);
            }
        }

        return true;
    }

    public void f(int[] numbs, int index, List<Integer> path, Set<List<Integer>> ans, int[] nums, int target) {

        if (index == numbs.length && path.size() == 3) {
            int sum = 0;
            for (Integer i : path) {
                sum += nums[i];
            }
            if (sum == target) {
                ans.add(new ArrayList<>(path));
            }
        } else {
            for (int i = index; i < numbs.length; i++) {
                if (!path.contains(i) && !ans.contains(path)) {
                    // 两种可能
                    // 1. 选择了numbs[i]
                    path.add(i);
                    f(numbs, i + 1, path, ans, nums, target);
                    path.remove(path.size() - 1);
                    // 2. 没有选择numbs[i]
                    f(numbs, i + 1, path, ans, nums, target);
                }
            }
        }
    }

}

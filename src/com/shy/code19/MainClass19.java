package com.shy.code19;

/**
 * @author haoyan.shi
 * @createDate 2020/12/24 17:43
 * description:分发糖果
 */
public class MainClass19 {
    public static void main(String[] args) {
        System.out.println(candy(new int[]{1, 2, 87, 87, 87, 2, 1}));
    }

    public static int candy(int[] ratings) {
        int[] left = new int[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 0, ret = 0;
        for (int i = ratings.length - 1; i >= 0; i--) {
            if (i < ratings.length - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            ret += Math.max(left[i], right);
        }
        return ret;

    }
}

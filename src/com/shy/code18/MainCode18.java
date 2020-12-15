package com.shy.code18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 石皓岩
 * @date 2020/12/15 7:03
 * 描述:单调递增的数字
 */
public class MainCode18 {
    public static void main(String[] args) {
        System.out.println(monotoneIncreasingDigits(3332));
    }

    public static int monotoneIncreasingDigits(int N) {
        // 首先应该搞出来一个数组  用来存储n的每一位的数字
        List<Integer> list = new ArrayList<>(10);
        while (N > 0) {
            list.add(N % 10);
            N /= 10;
        }
        // 现在来说我已经拿到了这个数字的倒叙的一个集合
        if (list.size() < 2) {
            return N;
        }
        Collections.reverse(list);
        Integer[] array = list.toArray(new Integer[list.size()]);
        // 开始处理这个数
        int i = 1;
        // 把循环条件改为这个数组是否单调递增
        while (!isOk(array)) {
            for (i = 1; i < array.length; i++) {
                // 首先判断一下当前位的前一位是不是小于本身
                if (array[i] >= array[i - 1]) {
                    continue;
                } else {
                    // 需要把前一位的数字减一
                    array[i - 1]--;
                    // 接下来把当前为的值设置为9
                    array[i] = 9;
                    break;
                }
            }
            for (; i < array.length; i++) {
                array[i] = 9;
            }
        }
        return getInt(array);
    }

    public static boolean isOk(Integer[] array) {
        // 需要把这个数组变成整数
        int a = getInt(array);
        Integer[] integers = Arrays.copyOf(array, array.length);
        Arrays.sort(integers);
        int b = getInt(integers);
        return a == b;
    }

    public static int getInt(Integer[] array) {
        int N = 0;
        int mi = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            N += array[i] * Math.pow(10, mi--);
        }
        return N;
    }
}

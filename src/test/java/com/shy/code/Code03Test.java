package com.shy.code;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code03Test {

    @Test
    public void test() {

        int[] arr = {0, 1, 0, 3, 2, 3};
        System.out.println(lengthOfLIS(arr));
    }

    public int lengthOfLIS(int[] nums) {

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int tag = nums[i];
            int count = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > tag) {
                    count++;
                    tag = nums[j];
                }
            }
            max = Math.max(max, count);
        }
        return max;
    }

    @Test
    public void test2() {

        Integer[][] matrix = generateMatrix(7);
        System.out.println(Arrays.deepToString(matrix));
    }


    // 螺旋矩阵
    public Integer[][] generateMatrix(int n) {

        Integer[][] matrix = new Integer[n][n];
        process(matrix, n - 1, n - 1, n * n, n, n);
        return matrix;

    }

    public void process(Integer[][] matrix, int row, int col, int n, int rowMax, int colMax) {

        if (row < 0 || row >= rowMax || col < 0 || col >= colMax || matrix[row][col] != null) {
            return;
        }

        matrix[row][col] = n;
        // 往下走到头 才能往左走到头
        if (row + 1 >= rowMax || matrix[row + 1][col] != null) {
            // 往左走到头
            process(matrix, row, col - 1, n - 1, rowMax, colMax);
        }

        // 需要往走到头
        if (col - 1 < 0 || matrix[row][col - 1] != null) {
            // 往上走到头
            process(matrix, row - 1, col, n - 1, rowMax, colMax);
        }

        // 需要往上走到头
        if (row - 1 < 0 || matrix[row - 1][col] != null) {
            // 往右走到头
            process(matrix, row, col + 1, n - 1, rowMax, colMax);
        }
        // 需要往右走到头
        if (col + 1 >= colMax || matrix[row][col + 1] != null) {
            // 往下走到头
            process(matrix, row + 1, col, n - 1, rowMax, colMax);
        }

    }

    @Test
    public void test3() {

        int[] nums = {3, 3};
        System.out.println(hasTrailingZeros(nums));
    }

    public boolean hasTrailingZeros(int[] nums) {

        int even = nums.length;
        for (int x : nums) {
            even -= x % 2;
        }
        return even >= 2;

    }

    /**
     * 将二维矩阵顺时针旋转90度
     * 该方法通过在原地交换矩阵中的元素来实现旋转，无需额外的内存开销
     *
     * @param matrix 一个二维矩阵，表示要旋转的矩阵
     */
    public void rotate(int[][] matrix) {
        // 获取矩阵的行数（或列数，因为矩阵是方阵）
        int n = matrix.length;

        // 遍历矩阵的前半部分行
        for (int i = 0; i < n / 2; i++) {
            // 遍历矩阵的前半部分列，包括中心列
            for (int j = 0; j < (n + 1) / 2; j++) {
                // 保存当前元素，以便进行交换
                int temp = matrix[i][j];
                // 将当前位置的元素旋转到新的位置
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }


    @Test
    public void test4() {

        int[][] intervals = {{1, 4}, {2, 3}};
        int[][] res = merge(intervals);

        System.out.println(Arrays.deepToString(res));
    }

    public int[][] merge(int[][] intervals) {

        if (intervals.length == 1) {
            return intervals;
        }
        // 可能需要按照开始区间进行排序
        List<List<Integer>> list = new ArrayList<>();
        // 对intervals 进行排序
        Arrays.sort(intervals, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            // 如果当前区间和前一个区间重合，就进行
            if (curAndPrevIntervalCoin(intervals, i, start, end)) {
                // 统计整合后的区间 作为前一个区间
                start = Math.min(intervals[i][0], start);
                end = Math.max(intervals[i][1], end);
                if (i == intervals.length - 1) {
                    // 需要把当前值也收录进去
                    List<Integer> last = new ArrayList<>();
                    last.add(start);
                    last.add(end);
                    list.add(last);
                }
            } else {
                // 同时把前面的结果收起来
                List<Integer> temp = new ArrayList<>();
                temp.add(start);
                temp.add(end);
                list.add(temp);
                // 需要把前面的收起来 同时把start和结束值为当前的开始和结束
                start = intervals[i][0];
                end = intervals[i][1];
                if (i == intervals.length - 1) {
                    // 需要把当前值也收录进去
                    List<Integer> last = new ArrayList<>();
                    last.add(start);
                    last.add(end);
                    list.add(last);
                }
            }
        }

        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> k = list.get(i);
            res[i][0] = k.get(0);
            res[i][1] = k.get(1);
        }
        return res;

    }

    private boolean curAndPrevIntervalCoin(int[][] intervals, int cur, int start, int end) {
        // 相同的情况
        if (intervals[cur][0] == start && intervals[cur][1] == end) {
            return true;
        }
        // 第二种， 当前区间的开始比前一个区间的开始大，但是比结束小，结束区间比前一个区间结束区间大
        if (intervals[cur][0] >= start && intervals[cur][0] <= end && intervals[cur][1] >= end) {
            return true;
        }

        if (intervals[cur][0] <= start && intervals[cur][1] <= end && intervals[cur][1] >= start) {
            return true;
        }
        if (intervals[cur][0] <= start && intervals[cur][1] >= end) {
            return true;
        }
        if (intervals[cur][0] >= start && intervals[cur][1] <= end) {
            return true;
        }
        return false;
    }

    private static final Object printController = new Object();

    private static int number = 1;

    public static void main(String[] args) {

        Thread oddThread = new Thread(new OddPrinter());
        Thread evenThread = new Thread(new EvenPrinter());

        oddThread.start();
        evenThread.start();
    }

    static class OddPrinter implements Runnable {


        @Override
        public void run() {

            while (number <= 100) {
                synchronized (printController) {
                    if (number % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + number++);
                        printController.notify();
                    }
                    try {
                        printController.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }

    static class EvenPrinter implements Runnable {

        @Override
        public void run() {

            while (number <= 100) {
                synchronized (printController) {
                    if (number % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + number++);
                        printController.notify();
                    }
                    try {
                        printController.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }

}

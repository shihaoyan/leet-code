package com.shy.code;

public class Code08Test {

    public static void main(String[] args) {

        int[][] matrix = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        Code08Test code08Test = new Code08Test();
        System.out.println(code08Test.minPathSum(matrix));
    }

    public int minPathSum(int[][] matrix) {
        // write code here
        return matrix[0][0] + process(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, new int[matrix.length + 1][matrix[0].length + 1]);
    }

    public int process(int[][] matrix, int row, int col, int tagRow, int tagCol, int[][] dp) {

        if (row > tagRow || col > tagCol) {
            return -1;
        }
        if (dp[row][col] != 0) {
            return dp[row][col];
        }
        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        int p3 = Integer.MAX_VALUE;
        if (row == tagRow && col == tagCol) {
            // System.out.println(matrix[row][col]);
            return matrix[row][col];
        }

        int temp1 = process(matrix, row, col + 1, tagRow, tagCol, dp) + matrix[row][col];
        if (temp1 != -1) {
            p1 = temp1;
        }


        int temp2 = process(matrix, row + 1, col, tagRow, tagCol, dp) + matrix[row][col];
        if (temp2 != -1) {
            p2 = temp2;
        }
        int temp3 = process(matrix, row + 1, col, tagRow, tagCol, dp) + process(matrix, row, col + 1, tagRow, tagCol, dp) + matrix[row][col];
        if (temp3 != -1) {
            p3 = temp3;
        }
        dp[row][col] = Math.min(p1, Math.min(p2, p3));
        return dp[row][col];

    }

}

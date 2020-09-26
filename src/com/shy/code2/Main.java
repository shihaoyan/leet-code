package com.shy.code2;

/**
 * @author 石皓岩
 * @createDate 2020-09-14 8:15
 * 描述:
 */
public class Main {
    public static void main(String[] args) {
        /*
        -5
        -5
        0
        3
        -3
        -3
        3
        3
         */
        System.out.println(computeArea(-5, -5, 0, 3, -3, -3, 3, 3));
    }

    public static int computeArea(int A, int B,
                                  int C, int D,

                                  int E, int F,
                                  int G, int H) {
        // 首先判断两个矩形是否相交
        int x = 0;
        int y = 0;
        if (E < C && G > A) {
            if (E >= A && G >= C) {
                x = C - E;
            } else if (E >= A && G <= C) {
                x = G - E;
            } else if (E <= A && G <= C) {
                x = G - A;
            } else if (E <= A && G >= C) {
                x = C - A;
            } else if (C - A == 0 || D - B == 0) {
                x = 0;
            }
        }
        if (H > B && F < D) {
            if (F <= B && H <= D) {
                y = H - B;
            } else if (F <= B && H >= D) {
                y = D - B;
            } else if (F >= B && H <= D) {
                y = H - F;
            } else if (F >= B && H >= D) {
                y = D - F;
            } else if (G - E == 0 || H - F == 0) {
                y = 0;
            }
        }
        if (A == E && B == F && C == G && D == H) {
            return Math.abs((C - A) * (D - B));
        }
        // 计算总面积
        int sumArea = Math.abs((C - A) * (D - B)) + Math.abs((G - E) * (H - F)) - Math.abs(x * y);
        return sumArea;
    }
}

package com.shy.code4;

/**
 * @author 石皓岩
 * @createDate 2020-09-16 8:19
 * 描述:整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 *  示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MainCode4 {
    public static void main(String[] args) {

    }

    /**
     * 整数反转
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int num = x % 10;
            // 为什么大于7呢，因为int最大值个位数是7  如果num大于7的话，下面就是超过了，所以返回
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && num > 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && num < -8)) {
                return 0;
            }
            result = result * 10 + num;
            x = x / 10;
        }
        return result;
    }
}

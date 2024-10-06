package com.shy.code;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: shihaoyan
 * @date: 2024/9/14 11:22
 * @description:
 */
public class Code10Test {


    private final static Lock LOCK = new ReentrantLock();

    private final static Condition CONDITION_A = LOCK.newCondition();

    private final static Condition CONDITION_B = LOCK.newCondition();

    private static Integer NUMS = 0;


    public static void main(String[] args) {

        Play play = new Play();
        new Thread(play::printA, "A").start();
        new Thread(play::printB, "B").start();
    }

    /**
     * 两个人打球，A先发球，B接球，然后打出去
     */
    static class Play {


        public void printA() {

            while (true) {
                // 先进行加锁
                try {
                    LOCK.lock();
                    if (NUMS % 2 == 0) {
                        System.out.println(Thread.currentThread() + "ping");
                        NUMS++;
                        // 唤醒B现成
                        CONDITION_B.signal();
                    } else {
                        CONDITION_A.await();
                    }
                } catch (Exception e) {

                } finally {
                    LOCK.unlock();
                }

            }


        }

        public void printB() {
            while (true) {
                // 先进行加锁
                try {
                    LOCK.lock();
                    if (NUMS % 2 != 0) {
                        System.out.println(Thread.currentThread() + "pang");
                        NUMS++;
                        // 唤醒B现成
                        CONDITION_A.signal();
                    } else {
                        CONDITION_B.await();
                    }
                } catch (Exception e) {

                } finally {
                    LOCK.unlock();
                }

            }
        }


    }

    // 输入一个字符串，只有数字 + - ()  100+(10-20)

    public static int test(String str) {

        // 先把字符串转化为数据
        char[] charArray = str.toCharArray();
        // 声明一个栈结构
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int flag = 0;
        double sum = 0.0;
        Double A = null;
        // 从零0位置往下走记录当前的数据，如果走到了 + 或 - 需要把当前数转为int
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '+' || charArray[i] == '-') {
                // 如果碰到运算符 需要把前面的数据转为int
                if (A == null) {
                    A = Double.parseDouble(sb.toString());
                } else {
                    // 如果flag > 0 说明有括号了，需要先处理括号的素具


                    if (charArray[i] == '+') {
                        sum = A + Double.parseDouble(sb.toString());
                    } else {
                        sum = A - Double.parseDouble(sb.toString());
                    }
                    // A重置
                    A = null;
                }
                // 清空对象
                sb = new StringBuilder();
            }
            // 只有两种情况
            // 1. 开头就是数字,这种情况，往下走一定会遇到标识符
            if (charArray[i] != '(' && charArray[i] != ')') {
                sb.append(charArray);
            } else {
                // 如果说出现了括号，需要对括号中的数据进行特殊处理
                if (charArray[i] == '(') {
                    flag++;
                } else {
                    flag--;
                }

            }
        }


        return 0;
    }

}

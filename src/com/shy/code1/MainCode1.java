package com.shy.code1;

import com.shy.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 石皓岩
 * @date 2020-09-12 21:06
 * 描述:二叉树的层平均值
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 * 输入：
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 输出：[3, 14.5, 11]
 * 解释：
 * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode-cn.com/problems/average-of-levels-in-binary-tree">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MainCode1 {

    public static void main(String[] args) {
        List<Double> doubles = averageOfLevels(new TreeNode(1));
        System.out.println(doubles);

        Map<String, Object> map = new HashMap<>();
        map.put("123", null);
        //  执行averageOfLevels(new TreeNode(1));

        
        System.out.println(map);


    }

    public static List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Double> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int length = queue.size();
            double sum = 0.0;
            for (int i = 0; i < length; i++) {
                sum += root.val;
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
                root = queue.pop();
            }
            result.add((sum / length));
        }


        return result;
    }

    static class BlockQueue<T> {

        private static final Lock LOCK = new ReentrantLock();

        private static final Condition PROVIDER_CONDITION = LOCK.newCondition();

        private static final Condition CONSUMER_CONDITION = LOCK.newCondition();

        private static final Queue<Object> QUEUE = new LinkedList<>();

        private int size;


        BlockQueue(int size) {
            this.size = size;
        }

        public void add(Object o) {
            LOCK.lock();
            try {
                while (QUEUE.size() >= size) {
                    PROVIDER_CONDITION.await();
                }
                QUEUE.add(o);
                CONSUMER_CONDITION.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        }

    }

}

package com.shy.code;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Code07Test {

    @Test
    public void test() {

        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        System.out.println(levelOrder(head));
    }


    // 按层遍历二叉树方法
    public ArrayList<ArrayList<Integer>> levelOrder(Node head) {

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (head == null) {
            return res;
        }
        Queue<Node> currentLevel = new LinkedList<>(); // 假设这是初始层
        currentLevel.add(head); // 假设rootNode是根节点
        while (!currentLevel.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            Queue<Node> nextLevel = new LinkedList<>();
            while (!currentLevel.isEmpty()) {
                Node poll = currentLevel.poll();
                list.add(poll.value);
                if (poll.left != null) {
                    nextLevel.add(poll.left);
                }
                if (poll.right != null) {
                    nextLevel.add(poll.right);
                }
            }
            res.add(list);
            currentLevel = nextLevel;
        }

        return res;
    }

    // 生成二叉树结构
    static class Node {

        public int value;

        public Node left;

        public Node right;

        public Node(int data) {

            this.value = data;
        }

    }

}

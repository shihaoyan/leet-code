package com.shy.code9;

import com.shy.common.ListNode;

/**
 * @author 石皓岩
 * @date 2020/9/29 8:31
 * 描述:合并两个有序链表
 */
public class MainCode9 {
    public static void main(String[] args) {

    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        /**
         * 递归的方式
         */
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
    /**
     *  迭代的方式
     */
    private static ListNode getListNode(ListNode l1, ListNode l2) {
        // 定义一个新的链表用来接收数据
        ListNode result = new ListNode();
        ListNode temp = result;
        ListNode node1 = l1;
        ListNode node2 = l2;
        while (node1 != null && node2 != null) {
            if (node1.val > node2.val) {
                temp.next = node2;
                node2 = node2.next;
            } else {
                temp.next = node1;
                node1 = node1.next;
            }
            temp = temp.next;
        }
        if (node1 == null) {
            temp.next = node2;
        }
        if (node2 == null) {
            temp.next = node1;
        }
        return result;
    }
}

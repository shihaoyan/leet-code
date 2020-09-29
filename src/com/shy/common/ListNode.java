package com.shy.common;

/**
 * @author 石皓岩
 * @date 2020/9/29 8:32
 * 描述:通用链表结构
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

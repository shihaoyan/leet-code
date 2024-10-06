package com.shy.code;

public class Code09Test {


    public ListNode partition(ListNode head, int x) {

        if (head == null) {
            return null;
        }
        ListNode dumy = new ListNode(-1);
        dumy.next = head;
        ListNode prev = head;
        ListNode cur = head.next;
        ListNode k = head;
        while (cur != null) {
            if (cur.val >= x) {
                k = cur;
                cur = cur.next;
            } else {
                k.next = cur.next;
                ListNode temp = prev.next;
                prev.next = cur;
                cur.next = temp;
                prev = cur;
                cur = k.next;
            }

        }

        return dumy.next;

    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        Code09Test code09Test = new Code09Test();
        ListNode listNode = code09Test.partition(head, 3);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }


    public static class ListNode {

        int val;

        ListNode next;

        ListNode(int x) {

            val = x;
        }

    }


}

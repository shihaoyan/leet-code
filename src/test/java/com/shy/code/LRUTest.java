package com.shy.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shihaoyan
 * @date: 2024/9/11 17:56
 * @description:
 */
public class LRUTest {

    private Map<Integer, Node> maps;

    private Node head;

    private Node tail;

    private int capacity;

    public LRUTest(int capacity){
        this.capacity = capacity;
        maps = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }


    public void put(int key ,int value){
        if (maps.containsKey(key)) {
            // 如果已经有了，需要直接把数据移动到链表尾部
            moveNodeToTail(maps.get(key));
        } else {
            // 如果容量超出，需要把头节点的位置删除
            if (this.capacity == maps.size()) {
                // 需要删除头节点的数据
                Node next = head.next;
                delHeadNode(next);
                //  从mqp里面移除掉
                maps.remove(next.key);
            }
            // 直接把数据插入map
            Node node = new Node();
            node.key = key;
            node.value = value;
            // 同时把数据连接到双向链表尾部
            insertToTail(node);
            maps.put(key, node);
        }


    }

    private void delHeadNode(Node next) {
        next.pre.next = next.next;
        next.next.pre = next.pre;
    }

    private void insertToTail(Node node) {
        // 直接把节点插入到尾结点
        tail.pre.next = node;
        node.next = tail;
        node.pre = tail.pre;
        tail.pre = node;
    }

    private void moveNodeToTail(Node node) {
        // 把当前node删除
        delHeadNode(node);
        // 再创建新的node插入到尾部
        Node newNode = new Node();
        newNode.key = node.key;
        newNode.value = node.value;
        insertToTail(newNode);
    }

    public Integer get(int key){

        if (!maps.containsKey(key)) {
            return -1;
        }
        // 如果有了，那需要把这个节点的数据移动到链表尾部
        Node node = maps.get(key);
        moveNodeToTail(node);
        return node.value;

    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class Node {

        private int key;

        private int value;

        private Node pre;

        private Node next;


    }


}

package com.kay.ds;

import java.util.HashMap;
import java.util.Map;

/**
 * Least Recently Used - 最近最少使用
 * <p>
 * 1. get/put time cost O(1) 2. when size reaches the capacity, remove the item which hasn't been used for the longest
 * amount of time
 */
public class LRUCache {

    // key - node
    private Map<String, Node> keyNodeMap;

    private DoubleList cache;

    private int cap;

    public LRUCache(int cap) {
        this.cap = cap;
        keyNodeMap = new HashMap<>();
        cache = new DoubleList();
    }

    public String get(String key) {
        if (!this.keyNodeMap.containsKey(key)) {
            return null;
        }

        makeRecently(key);
        return this.keyNodeMap.get(key).val;
    }

    private void makeRecently(String key) {
        Node node = keyNodeMap.get(key);
        cache.remove(node); // remove from last
        cache.addLast(node); // add to first
    }

    public void put(String key, String value) {
        if (this.keyNodeMap.containsKey(key)) {
            deleteKey(key);
            addRecently(key, value);
        }

        if (this.cache.size() == this.cap) {
            removeLeastRecently();
        }

        addRecently(key, value);
    }

    private void deleteKey(String key) {
        Node remove = keyNodeMap.remove(key);
        cache.remove(remove);
    }

    private void addRecently(String key, String value) {
        Node x = new Node(key, value);
        cache.addLast(x);
        keyNodeMap.put(key, x);
    }

    private void removeLeastRecently() {
        Node deleted = cache.removeFirst();
        keyNodeMap.remove(deleted.key);
    }


    class Node {
        String key;
        String val;
        Node next, prev;

        public Node(String key, String val) {
            this.key = key;
            this.val = val;
        }
    }

    class DoubleList {
        private Node head;
        private Node tail;
        private int size;

        //head,tail 仅作为2个指针，head.next ==tail 时无数据
        public DoubleList() {
            head = new Node(null, null);
            tail = new Node(null, null);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void addLast(Node x) {
            x.prev = tail.prev;
            x.next = tail;
            tail.prev.next = x;
            tail.prev = x;
            size++;
        }

        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        public Node removeFirst() {
            if (head.next == tail) {
                return null;
            }

            Node first = head.next;
            remove(first);
            return first;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put("k1", "v1");
        cache.put("k2", "v2");

        cache.get("k1");

        cache.put("k3", "v3"); // removed k2

        assert cache.get("k2") == null;
    }
}

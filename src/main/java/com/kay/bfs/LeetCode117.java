package com.kay.bfs;

public class LeetCode117 {

    static class Solution {
        public Node connect(Node root) {
            Node head = root;
            Node temp = new Node(0);

            while (head != null) {
                Node cur = head;
                Node child = temp;
                while (cur != null) {
                    if (cur.left != null) {
                        child.next = cur.left;
                        child = cur.left;
                    }

                    if (cur.right != null) {
                        child.next = cur.right;
                        child = child.right;
                    }
                    cur = cur.next;
                }
                head = temp.next;
                temp.next = null;
            }

            return root;
        }
    }
}

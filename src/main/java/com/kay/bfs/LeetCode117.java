package com.kay.bfs;

public class LeetCode117 {

    /**
     * the idea is same as using Queue in BFS, level-order traversal
     */
    static class Solution {
        public Node connect(Node root) {
            if (root == null) {
                return null;
            }

            Node cur = root; // current level
            while (cur != null) {

                Node dummy = new Node(0); // used to link next level list
                Node prev = dummy;

                while (cur != null) {
                    if (cur.left != null) {
                        prev.next = cur.left;
                        prev = prev.next;
                    }

                    if (cur.right != null) {
                        prev.next = cur.right;
                        prev = prev.next;
                    }
                    cur = cur.next;
                }

                cur = dummy.next; //move to next level
            }
            return root;
        }
    }
}

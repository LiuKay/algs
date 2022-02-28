package com.kay.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode116 {

    /**
     * with no extra space. Recursively
     */
    static class SolutionRecursive {
        public Node connect(Node root) {
            if (root == null) {
                return root;
            }
            recursive(root);
            return root;
        }

        void recursive(Node node) {
            if (node == null) {
                return;
            }

            if (node.left != null) {
                node.left.next = node.right;
            }
            if (node.right != null && node.next != null) {
                node.right = node.next.left;
            }
            recursive(node.left);
            recursive(node.right);
        }

    }

    /**
     * time O(N), space O(N)
     */
    static class Solution {
        public Node connect(Node root){
            Node levelNode = root;
            while (levelNode != null) {
                Node cur = levelNode;

                while (cur != null) {
                    if (cur.left != null) {
                        cur.left.next = cur.right;
                    }

                    if (cur.right != null && cur.next != null) {
                        cur.right.next = cur.next.left;
                    }
                    cur = cur.next;
                }
                levelNode = levelNode.left;
            }
            return root;
        }
    }

    /**
     * Use BFS, time O(N), space O(N)
     */
    static class BFSSolution {
        public Node connect(Node root) {
            if (root == null) {
                return null;
            }
            Queue<Node> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {
                int size = q.size();

                Node prev = null;
                for (int i = 0; i < size; i++) {
                    Node cur = q.poll();

                    if (prev != null) {
                        prev.next = cur;
                    }
                    prev = cur;

                    if (cur.left != null) {
                        q.offer(cur.left);
                    }
                    if (cur.right != null) {
                        q.offer(cur.right);
                    }

                }
                prev.next = null;
            }

            return root;

        }
    }

}

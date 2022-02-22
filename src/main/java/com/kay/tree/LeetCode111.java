package com.kay.tree;

import com.kay.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS, DFS
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 */
public class LeetCode111 {

    static class Solution {

        //broad-first search
        int minDepthInBFS(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int depth = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    if (cur.left == null && cur.right == null) {
                        return depth;
                    }

                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }
                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                }
                depth++;
            }
            return depth;
        }

        //depth-first search
        int minDepthInDFS(TreeNode root) {
            if (root == null) {
                return 0;
            }

            if (root.left == null) {
                return minDepthInBFS(root.right) + 1;
            }
            if (root.right == null) {
                return minDepthInBFS(root.left) + 1;
            }

            return Math.min(minDepthInBFS(root.left), minDepthInBFS(root.right)) + 1;
        }

    }

}

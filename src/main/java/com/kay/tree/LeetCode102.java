package com.kay.tree;

import com.kay.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BFS
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class LeetCode102 {

    static class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> path = new LinkedList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    path.add(cur.val);

                    if (cur.right == null && root.left == null) {
                        continue;
                    }
                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }
                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                }
                res.add(path);
            }
            return res;
        }

    }

}

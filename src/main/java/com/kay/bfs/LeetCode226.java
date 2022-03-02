package com.kay.bfs;

import com.kay.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode226 {

    static class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                TreeNode left = cur.left;
                cur.left = cur.right;
                cur.right = left;

                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            return root;
        }
    }
}

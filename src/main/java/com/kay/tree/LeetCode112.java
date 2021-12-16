package com.kay.tree;

import com.kay.TreeNode;

/**
 * https://leetcode.com/problems/path-sum/
 */
public class LeetCode112 {

    static class Solution {
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null)
                return false;

            if (root.left == null && root.right == null)
                return targetSum == root.val;

            return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);

        }
    }
}

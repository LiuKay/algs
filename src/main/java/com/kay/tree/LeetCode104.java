package com.kay.tree;

import com.kay.TreeNode;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class LeetCode104 {
    static class Solution {
        public int maxDepth(TreeNode root) {
            if(root == null){
                return 0;
            }
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);

            return 1+ Math.max(left,right);
        }
    }
}

package com.kay.tree;

import com.kay.Assert;
import com.kay.TreeNode;
import com.kay.tree.tool.BinaryTreeSerializers;

/**
 * https://leetcode.com/problems/symmetric-tree/
 */
public class LeetCode101 {

    public static void main(String[] args) {
        String content = "1,2,2,2,#,2,#,";
        final TreeNode treeNode = BinaryTreeSerializers.BREAD_FIRST_ORDER.deserialize(content);
        final boolean symmetric = new Solution().isSymmetric(treeNode);
        Assert.isFalse(symmetric);
    }

    static class Solution{
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return false;
            }
            return traverse(root.left, root.right);
        }

        boolean traverse(TreeNode left, TreeNode right){
            if (left == null || right == null) {
                return left == right;
            }
            if (left.val != right.val) {
                return false;
            }
            return traverse(left.left, right.right) && traverse(left.right, right.left);
        }

    }
}

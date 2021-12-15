package com.kay.tree;

import com.kay.Assert;
import com.kay.TreeNode;
import com.kay.ds.BST;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class LeetCode108 {

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        final TreeNode treeNode = new Solution().sortedArrayToBST(nums);

        Assert.isTrue(BST.isValidBST(treeNode));

    }

    static class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            if (nums == null || nums.length == 0) {
                return null;
            }

            return traverse(nums, 0, nums.length - 1); // note ArrayIndexOutOfBoundsException
        }

        TreeNode traverse(int[] nums, int low, int high) {
            if (high < low) {
                return null;
            }
            int p = (low + high) / 2;
            TreeNode root = new TreeNode(nums[p]);
            root.left = traverse(nums, low, p-1);
            root.right = traverse(nums, p + 1, high);
            return root;
        }

    }
}

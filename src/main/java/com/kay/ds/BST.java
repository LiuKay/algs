package com.kay.ds;

import com.kay.Assert;
import com.kay.TreeNode;
import com.kay.Utils;

public class BST {

    public static void main(String[] args) {
        final int[] nums = Utils.random(10);

        TreeNode root = null;
        for (int num : nums) {
            root = insertBST(root, num);
        }

        assert isValidBST(root);

       Utils.preOrderPrint(root);

//        Utils.printArray(nums);

        final TreeNode node = remove(root, nums[0]);

        Assert.isTrue(isValidBST(node));
    }

    public static TreeNode numsToBST(int[] nums) {
        TreeNode root = null;
        for (int num : nums) {
            root = insertBST(root, num);
        }
        return root;
    }

    //left <= root <= right
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private static boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null)
            return true;

        if (min != null && root.val < min.val) {
            return false;
        }
        if (max != null && root.val > max.val) {
            return false;
        }
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, null);
    }

    //insert a value into BST
    public static TreeNode insertBST(TreeNode root, int target) {
        if (root == null) {
            return new TreeNode(target);
        }

        if (root.val == target) {
            return root;
        } else if (root.val < target) {
            root.right = insertBST(root.right, target);
        } else {
            root.left = insertBST(root.left, target);
        }
        return root;
    }

    public static TreeNode remove(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        if (root.val == target) {
            //remove
            if(root.left == null)
                return root.right;
            if (root.right == null) {
                return root.left;
            }

            TreeNode min = getMin(root.right);
            root.val = min.val;
            root.right = remove(root.right, min.val);
        } else if (root.val < target) {
            root.right = remove(root.right, target);
        } else {
            root.left = remove(root.left, target);
        }
        return root;
    }

    public static TreeNode getMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}

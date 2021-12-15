package com.kay.tree.bst;

import com.kay.Assert;
import com.kay.TreeNode;
import com.kay.Utils;
import com.kay.ds.BST;
import com.kay.tree.BinaryTreeSerializers;

/**
 * <a>https://leetcode.com/problems/recover-binary-search-tree/</a>
 */
public class LeetCode99 {

    public static void main(String[] args) {
        String content = "5,3,9,-2147483648,2,";
        final TreeNode treeNode = BinaryTreeSerializers.BREAD_FIRST_ORDER.deserialize(content);

        Assert.isFalse(BST.isValidBST(treeNode));
        System.out.println(BinaryTreeSerializers.IN_ORDER.serialize(treeNode));

        new Solution().recoverTree(treeNode);
        System.out.println(BinaryTreeSerializers.IN_ORDER.serialize(treeNode));

        Assert.isTrue(BST.isValidBST(treeNode));
    }

    static class Solution {

        /**
         * in order traverse
         * compare previous node with current node, if not in order, the node is in the wrong position
         */

        //two wrong node
        TreeNode firstNode = null;
        TreeNode secondNode = null;
        TreeNode prevNode = null;

        public void recoverTree(TreeNode root) {
            if (root == null) {
                return;
            }
            traverse(root);

            //swap
            int temp = firstNode.val;
            firstNode.val = secondNode.val;
            secondNode.val = temp;
        }

        private void traverse(TreeNode root){
            if (root == null) {
                return;
            }

            traverse(root.left);

            if (prevNode != null) {
                //means the prev node value larger than current, find the first one
                if (firstNode == null && prevNode.val >= root.val) {
                    firstNode = prevNode;
                }
            }

            //current should larger than prev node. find the second
            if (firstNode != null && prevNode.val >= root.val) {
                secondNode = root;
            }
            prevNode = root;

            traverse(root.right);
        }
    }

}

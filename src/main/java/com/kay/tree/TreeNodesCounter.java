package com.kay.tree;


import com.kay.TreeNode;

/**
 * Complete Binary Tree - 完全二叉树，每一层紧凑靠左排列 Perfect Binary Tree - 满二叉树，每一层都是满的 Full Binary Tree - 所有节点要么没有子节点，要么有2个子节点
 */
public class TreeNodesCounter {

    //O(N) for plain Binary Tree
    static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    //满二叉树，节点数与高度成指数关系
    //O(logN)
    static int countNodesPBT(TreeNode root) {
        int h = 0;
        while (root != null) {
            root = root.left;
            h++;
        }
        return (int) Math.pow(2, h) - 1;
    }


    //完全二叉树，O(logN * logN)
    //Note: 完全二叉树紧凑的靠左排列，所以其中某个子树一定会是一个满二叉树，也就是会遇到 hl == hr
    static int countNodesCBT(TreeNode root) {
        TreeNode l = root, r = root;
        int hl = 0, hr = 0;
        while (l != null) {
            l = l.left;
            hl++;
        }
        while (r != null) {
            r = r.right;
            hr++;
        }
        //left height == right height => Perfect Binary Tree
        if (hl == hr) {
            return (int) Math.pow(2, hl) - 1;
        }

        return 1 + countNodesCBT(root.left) + countNodesCBT(root.right);
    }
}

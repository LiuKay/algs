package com.kay.tree;

import com.kay.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Backtracking
 * https://leetcode.com/problems/binary-tree-paths/
 */
public class LeetCode257 {

    // ["1->2->5","1->3"]
    static class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new LinkedList<>();
            backtrack(root,new LinkedList<>(),res);
            return res;
        }

        private void backtrack(TreeNode root, LinkedList<Integer> path, List<String> res) {
            if (root == null) {
                return;
            }
            path.add(root.val); //step into
            if (root.left == null && root.right == null) {
                //can be replaced by any operation else.
                res.add(path.stream().map(String::valueOf).collect(Collectors.joining("->")));
            }else {
                backtrack(root.left, path, res);
                backtrack(root.right, path, res);
            }
            path.removeLast(); // backtrack
        }
    }


    List<List<Integer>> getAllPath(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        getPath(root, new LinkedList<>(), res);
        return res;
    }

    void getPath(TreeNode root, LinkedList<Integer> path, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null) {
            res.add(new LinkedList<>(path));
        }else{
            getPath(root.left, path, res);
            getPath(root.right, path, res);
        }
        path.removeLast();

    }


}

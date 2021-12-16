package com.kay.tree;

import com.kay.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/path-sum-ii/
 *
 * Depth-First Search
 */
public class LeetCode113 {
    static class Solution {

        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            pathSum(root,targetSum,result,list);
            return result;
        }

        private void pathSum(TreeNode root, int targetSum, List<List<Integer>> result, List<Integer> list) {
            if (root == null) {
                return;
            }

            list.add(root.val);
            if (root.left == null && root.right == null && targetSum == root.val) {
                result.add(new ArrayList<>(list));
            }else {
                pathSum(root.left, targetSum - root.val, result, list);
                pathSum(root.right, targetSum - root.val, result, list);
            }
            list.remove(list.size() - 1);
        }

    }
}

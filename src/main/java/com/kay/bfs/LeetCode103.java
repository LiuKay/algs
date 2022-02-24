package com.kay.bfs;

import com.kay.Assert;
import com.kay.TreeNode;
import com.kay.tree.tool.BinaryTreeSerializers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class LeetCode103 {


    public static void main(String[] args) {
        String content = "3,9,20,#,#,15,7";
        final TreeNode treeNode = BinaryTreeSerializers.BREAD_FIRST_ORDER.deserialize(content);

        final List<List<Integer>> lists = new Solution().zigzagLevelOrder(treeNode);
//        System.out.println(lists);

        Assert.isEquals(lists.toString(), "[[3], [20, 9], [15, 7]]");
    }

    static class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();

            if (root == null)
                return res;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            int level = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();

                LinkedList<Integer> list = new LinkedList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();

                    if (level % 2 == 0) {
                        list.addLast(cur.val);
                    } else {
                        list.addFirst(cur.val);
                    }

                    if (cur.left == null && cur.right == null) {
                        continue;
                    }

                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }
                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                }
                res.add(list);
                level++;
            }
            return res;
        }
    }
}

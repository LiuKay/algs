package com.kay.bfs;

import com.kay.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode107 {

    static class Solution {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> res = new LinkedList<>();

            if (root == null)
                return res;

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {
                int size = q.size();
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();

                    list.add(cur.val);
                    if (cur.left != null) {
                        q.offer(cur.left);
                    }

                    if (cur.right != null) {
                        q.offer(cur.right);
                    }
                }
                res.add(0, list);
            }
            return res;
        }
    }

}

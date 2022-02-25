package com.kay.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <href><a>https://leetcode.com/problems/subsets/</a></href>
 *
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class LeetCode78 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        final List<List<Integer>> subsets = new Solution().subsets(nums);

        System.out.println(subsets);
    }

    static class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();

            backtrack(res, new LinkedList<>(), nums, 0);
            return res;
        }

        private void backtrack(List<List<Integer>> res, LinkedList<Integer> path, int[] nums, int start) {
            res.add(new ArrayList<>(path));

            for (int i = start; i < nums.length; i++) {
                path.add(nums[i]);
                backtrack(res, path, nums, i + 1);
                path.removeLast();
            }
        }
    }

}

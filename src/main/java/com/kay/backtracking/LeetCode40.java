package com.kay.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 */
public class LeetCode40 {

    public static void main(String[] args) {

        int[] candidates = {10,1,2,7,6,1,5};

        final List<List<Integer>> lists = new Solution().combinationSum2(candidates, 8);

        System.out.println(lists);

    }


    static class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(candidates);
            backtrack(candidates, res, new ArrayList<>(), target, 0);
            return res;
        }

        void backtrack(int[] nums, List<List<Integer>> res, List<Integer> path, int remain, int start) {
            if (remain == 0) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i < nums.length; i++) {
                //skip the same num in the path
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }

                if (remain - nums[i] < 0) {
                    continue;
                }

                path.add(nums[i]);
                backtrack(nums, res, path, remain - nums[i], i + 1); //each candidate can only be used once
                path.remove(path.size() - 1);
            }

        }
    }
}

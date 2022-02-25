package com.kay.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 */
public class LeetCode39 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 6, 7};
        final List<List<Integer>> lists = new Solution().combinationSum(nums, 7);
        System.out.println(lists);
    }


    static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
//            Arrays.sort(candidates);
            backtrack(target, new ArrayList<>(), candidates, res, 0);
            return res;
        }

        void backtrack(int target, List<Integer> path, int[] nums, List<List<Integer>> res, int start) {
            if (target == 0) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i < nums.length; i++) {
                if (target - nums[i] < 0) {
                    continue;
                }

                path.add(nums[i]);
                backtrack(target - nums[i], path, nums, res, i); //each candidate can be used an unlimited number of times
                path.remove(path.size() - 1);
            }
        }

    }

}

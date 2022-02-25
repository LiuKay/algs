package com.kay.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Subsets II
 */
public class LeetCode90 {

    static class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            backtrack(res, new LinkedList<>(), nums, 0);
            return res;
        }

        void backtrack(List<List<Integer>> res, LinkedList<Integer> path, int[] nums, int start) {
            res.add(new ArrayList<>(path));

            for (int i = start; i < nums.length; i++) {

                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }

                path.add(nums[i]);
                backtrack(res, path, nums, i + 1);
                path.removeLast();
            }
        }
    }
}

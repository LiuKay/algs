package com.kay.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Permutations
 */
public class LeetCode46 {

    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();

            backtrack(res, new ArrayList<>(), nums);
            return res;
        }

        void backtrack(List<List<Integer>> res, List<Integer> path, int[] nums) {
            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (path.contains(nums[i])) {
                    continue;
                }

                path.add(nums[i]);
                backtrack(res, path, nums);
                path.remove(path.size() - 1);
            }
        }
    }

}

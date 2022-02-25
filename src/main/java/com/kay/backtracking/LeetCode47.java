package com.kay.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a collection of numbers, nums, that might contain duplicates,
 * return all possible unique permutations in any order.
 */
public class LeetCode47 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        final List<List<Integer>> lists = new Solution().permuteUnique(nums);

        System.out.println(lists);
    }

    static class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);

            boolean[] used = new boolean[nums.length];
            backtrack(res, new ArrayList<>(), used, nums);
            return res;
        }

        void backtrack(List<List<Integer>> res, List<Integer> path, boolean[] used, int[] nums) {
            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }

                //!used[i-1] means the last iteration of the same number, the value of used[i-1] was reset to false.
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }

                used[i] = true;

                path.add(nums[i]);
                backtrack(res, path, used, nums);
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }

    }

    static class SolutionUsingHashMap {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);

            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            backtrack(res, new ArrayList<>(), map, nums);
            return res;
        }

        void backtrack(List<List<Integer>> res, List<Integer> path, Map<Integer, Integer> map, int[] nums) {
            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                final int cur = nums[i];
                if (i > 0 && cur == nums[i - 1]) {
                    continue;
                }

                map.put(cur, map.get(cur) - 1);
                path.add(cur);
                backtrack(res, path, map, nums);
                path.remove(path.size() - 1);
                map.put(cur, map.get(cur) + 1);
            }
        }

    }

}

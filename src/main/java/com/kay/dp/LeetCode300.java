package com.kay.dp;

import com.kay.Assert;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class LeetCode300 {


    /**
     * Input: nums = [10,9,2,5,3,7,101,18]
     * Output: 4
     * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
     *
     * dp[i] : 以 nums[i] 结尾的最长递增子序列的长度
     *
     * dp[0...i-1] => dp[i]:
     * if nums[i] > nums[0...i-1] (increasing)
     *      dp[i] = max(dp[0...i-1] + 1)
     *
     *
     *
     */
    static class Solution {

        public int lengthOfLIS(int[] nums) {

            int[] dp = new int[nums.length];
            //base case
            Arrays.fill(dp, 1);

            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }

            int res = 0;
            for (int i = 0; i < dp.length; i++) {
                res = Math.max(res, dp[i]);
            }

            return res;
        }


        //patience game
        public int lengthOfLISByBinarySearch(int[] nums){
            int[] tops = new int[nums.length]; // the tops is sorted.

            int piles = 0;
            for (int i = 0; i < nums.length; i++) {
                int target = nums[i];

                int left = 0;
                int right = piles;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (tops[mid] == target) {
                        right = mid;
                    } else if (tops[mid] > target) {
                        right = mid;
                    } else if (tops[mid] < target) {
                        left = mid + 1;
                    }
                }

                //no pile can put on, add a new pile
                if (left == piles) {
                    piles++;
                }

                //put the target on the top
                tops[left] = target;
            }
            return piles;
        }
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        final Solution solution = new Solution();
        final int length = solution.lengthOfLIS(nums);

        Assert.isTrue(length == 4);

        Assert.isEquals(4, solution.lengthOfLISByBinarySearch(nums));
    }
}

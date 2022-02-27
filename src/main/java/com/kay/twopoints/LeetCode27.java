package com.kay.twopoints;

import com.kay.Assert;

public class LeetCode27 {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int i = new Solution().removeElement(nums, 2);

        Assert.isEquals(i, 5);
    }

    static class Solution {
        public int removeElement(int[] nums, int val) {
            int i = 0;

            // 'i' is the position which can be replaced.
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] != val) {
                    nums[i++] = nums[j];
                }
            }
            return i;
        }
    }

}

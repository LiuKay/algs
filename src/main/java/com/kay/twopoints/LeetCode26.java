package com.kay.twopoints;

public class LeetCode26 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        int i = new Solution().removeDuplicates(nums);

        System.out.println(i);
    }

    static class Solution {
        public int removeDuplicates(int[] nums) {
            int i = 0;
            // num 'i' is point to the last element which is not duplicate.
            for (int j = 1; j < nums.length; j++) {
                if (nums[j] != nums[i]) {
                    i++;
                    nums[i] = nums[j];
                }
            }
            return i + 1;
        }
    }

}

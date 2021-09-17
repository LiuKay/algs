package com.kay.leetcode;

public class ContainsMostWater {

    static class TwoPoints {
        public int maxArea(int[] height) {
            int n = height.length;
            int maxArea = 0;
            int left = 0;
            int right = n - 1;

            while (left < right) {
                int h = height[left] < height[right] ? height[left++] : height[right--];
                int curArea = (right - left + 1) * h;
                maxArea = Math.max(curArea, maxArea);

            }
            return maxArea;
        }

    }

}

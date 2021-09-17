package com.kay.leetcode;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class TrappingRainWater {

    //O(N^2)
    static class Violent {
        public static int trap(int[] height) {
            int n = height.length;
            int trap = 0;
            for (int i = 1; i < n; i++) {
                int l_max = 0, r_max = 0;
                //find right max for i
                for (int j = i; j < n; j++) {
                    r_max = max(r_max, height[j]);
                }
                //find left max for i
                for (int k = i; k >= 0; k--) {
                    l_max = max(l_max, height[k]);
                }
                trap += Math.min(l_max, r_max) - height[i];
            }
            return trap;
        }
    }

    static class BackUp {
        public static int trap(int[] height) {
            int n = height.length;
            int sum = 0;

            int[] l_max = new int[n]; // 每个位置左边的最高柱子
            int[] r_max = new int[n]; // 每个位置右边的最高柱子

            l_max[0] = height[0];
            r_max[n - 1] = height[n - 1];
            for (int i = 1; i < n; i++) {
                l_max[i] = max(height[i], l_max[i - 1]);
            }
            for (int i = n - 2; i >= 0; i--) {
                r_max[i] = max(height[i], r_max[i + 1]);
            }

            for (int i = 0; i < n; i++) {
                sum += min(l_max[i], r_max[i]) - height[i];
            }

            return sum;
        }
    }

    static class TwoPoints {
        public static int trap(int[] height) {
            int n = height.length;
            int left = 0, right = n - 1;
            int sum = 0;

            int l_max = height[0]; //0~left 最高
            int r_max = height[n - 1]; //right~n-1 最高
            while (left < right) {
                l_max = max(l_max, height[left]);
                r_max = max(r_max, height[right]);

                if (l_max <= r_max) {
                    sum += l_max - height[left];
                    left++;
                } else {
                    sum += r_max - height[right];
                    right--;
                }
            }

            return sum;
        }
    }

}

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * Note: Given n will be a positive integer.
 */

package com.kay.leetcode.arrary;

import com.kay.Assert;

/**
 * Created on 2020/5/22
 *
 * @author: LiuKay
 */
public class ClimbingStairs {


    public static void main(String[] args) {
        final int stairs = Solution.climbStairs(50);
        final int recursively = Solution.climbStairsRecursively(50);
        Assert.isTrue(stairs == recursively);
    }

    static class Solution {

        public static int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }

            int f1 = 1, f2 = 2;
            for (int i = 3; i <= n; i++) {
                int f3 = f1 + f2;
                f1 = f2;
                f2 = f3;
            }
            return f2;
        }

        public static int climbStairsRecursively(int n) {
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            return climbStairsRecursively(n - 2) + climbStairsRecursively(n - 1);
        }
    }
}

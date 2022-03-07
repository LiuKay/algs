package com.kay.twopoints;

import com.kay.Assert;

public class LeetCode633 {

    public static void main(String[] args) {
        Assert.isTrue(new Solution().judgeSquareSum(2147483600));
    }

    static class Solution {

        public boolean judgeSquareSum(int c) {
            if (c < 0) {
                return false;
            }
            int i = 0;
            int j = (int) Math.sqrt(c);
            while (i <= j) {
                int t = c - i * i; // avoid overflow
                if (t == j * j) {
                    return true;
                } else if (t > j * j) {
                    i--;
                } else {
                    j--;
                }
            }
            return false;
        }

        public boolean judgeSquareSum2(int c) {
            for (long a = 0; a * a <= c; a++) {
                int b = c - (int) (a * a);
                if (binarySearch(0, b, b))
                    return true;
            }
            return false;
        }

        public boolean binarySearch(long s, long e, int n) {
            if (s > e)
                return false;
            long mid = s + (e - s) / 2;
            if (mid * mid == n)
                return true;
            if (mid * mid > n)
                return binarySearch(s, mid - 1, n);
            return binarySearch(mid + 1, e, n);
        }

    }
}

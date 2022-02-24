package com.kay.leetcode;

import com.kay.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Sliding Window
 *
 * https://leetcode.com/problems/minimum-window-substring/
 */
public class LeetCode76 {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        final String res = new Solution().minWindow(s, t);

        Assert.isEquals(res,"BANC");
    }

    static class Solution {

        public String minWindow(String s, String t) {
            Map<Character, Integer> window = new HashMap<>();
            Map<Character, Integer> needs = new HashMap<>();

            for (char c : t.toCharArray()) {
                needs.put(c, needs.getOrDefault(c, 0) + 1);
            }

            int left = 0;
            int right = 0;
            int index=0;
            int length = Integer.MAX_VALUE;
            int valid = 0;
            while (right < s.length()) {
                char c = s.charAt(right);
                right++;
                if (needs.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if (window.get(c).equals(needs.get(c))) {
                        valid++;
                    }
                }

                while (valid == needs.size()) {
                    if (right - left < length) {
                        index = left;
                        length = right - left;
                    }

                    char l = s.charAt(left);
                    left++;
                    if (needs.containsKey(l)) {
                        if (window.get(l).equals(needs.get(l))) {
                            valid--;
                        }
                        window.put(l, window.get(l) - 1);
                    }
                }
            }
            return length == Integer.MAX_VALUE ? "" : s.substring(index, index + length);
        }
    }

}

package com.kay.slidingwindow;

import com.kay.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Sliding Window
 */
public class LeetCode3 {

    public static void main(String[] args) {
        String input = "abcabcbb";
        final int res = new Solution().lengthOfLongestSubstring1(input);
        Assert.isEquals(res, 3);

        String input1 = "au";
        final int res1 = new Solution().lengthOfLongestSubstring1(input1);
        Assert.isEquals(res1, 2);
    }

    static class Solution {

        //[left,right)
        public int lengthOfLongestSubstring(String s) {
            if (s == null) {
                return 0;
            }

            Set<Character> needs = new HashSet<>();
            int left = 0;
            int right = 0;
            int length = 0;
            while (right < s.length()) {
                char c = s.charAt(right);
                right++;           //move right

                while (needs.contains(c)) {
                    //left -> first not repeatable
                    needs.remove(s.charAt(left));
                    left++; //move left
                }
                needs.add(c);
                length = Math.max(length, right - left);
            }

            return length;
        }

        //[left,right]
        public int lengthOfLongestSubstring1(String s) {
            if (s == null) {
                return 0;
            }

            Map<Character, Integer> map = new HashMap<>(); //store index for each character
            int left = 0;
            int right = 0;
            int length = 0;
            while (right < s.length()) {
                char c = s.charAt(right);
                right++;

                if (map.containsKey(c)) {
                    //move to the next position of duplicate char
                    left = Math.max(map.get(c) + 1, left);
                }
                map.put(c, right);
                length = Math.max(length, right - left);
            }

            return length;
        }
    }

}

package com.kay.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Palindrome Partitioning
 * <p>
 * tips: each element use only once
 */
public class LeetCode131 {

    public static void main(String[] args) {
        List<List<String>> partition = new Solution().partition("aab");

        System.out.println(partition);
    }

    static class Solution {
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            dfs(res, s, new ArrayList<>(), 0);
            return res;
        }

        private void dfs(List<List<String>> res, String s, ArrayList<String> path, int start) {
            if (start == s.length()) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i < s.length(); i++) {
                if (isPalindrome(s, start, i)) {
                    path.add(s.substring(start, i + 1));
                    dfs(res, s, path, i + 1);
                    path.remove(path.size() - 1);
                }
            }

        }

        boolean isPalindrome(String s, int low, int high) {
            while (low < high) {
                if (s.charAt(low++) != s.charAt(high--)) {
                    return false;
                }
            }
            return true;
        }
    }

}

package com.kay.backtracking;

import com.kay.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode17 {

    public static void main(String[] args) {
        String digits = "23";
        final List<String> list = new Solution().letterCombinations(digits);

        Assert.isEquals(list.toString(),"[ad, ae, af, bd, be, bf, cd, ce, cf]");
    }



    static class Solution {

        private static Map<Character, String> map = new HashMap<>();

        static {
            map.put('2', "abc");
            map.put('3', "def");
            map.put('4', "ghi");
            map.put('5', "jkl");
            map.put('6', "mno");
            map.put('7', "pqrs");
            map.put('8', "tuv");
            map.put('9', "wxyz");
        }

        public List<String> letterCombinations(String digits) {
            List<String> res = new ArrayList<>();
            if ("".equals(digits)) {
                return res;
            }

            backtrack(res, digits,new StringBuilder(), 0);
            return res;
        }

        private void backtrack(List<String> res, String digits, StringBuilder path, int start) {
            if (path.length() == digits.length()) {
                res.add(path.toString());
                return;
            }

            final char key = digits.charAt(start);
            final String list = map.get(key);

            final char[] chars = list.toCharArray();
            for (char c : chars) {
                path.append(c);
                backtrack(res, digits, path, start + 1);
                path.deleteCharAt(path.length() - 1);
            }
         }
    }

}

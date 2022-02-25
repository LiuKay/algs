package com.kay.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LeetCode22 {

    static class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            backtrack(res, new StringBuilder(), n, n);
            return res;
        }

        private void backtrack(List<String> res, StringBuilder path, int left, int right) {
            if (left < 0 || right < 0) {
                return;
            }
            if (right < left) {
                return;
            }

            if (left == 0 && right == 0) {
                res.add(path.toString());
                return;
            }

            path.append("(");
            backtrack(res, path, left - 1, right);
            path.deleteCharAt(path.length() - 1);

            path.append(")");
            backtrack(res, path, left, right - 1);
            path.deleteCharAt(path.length() - 1);
        }
    }

}

package com.kay.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * N-Queens
 * <p>
 * https://leetcode.com/problems/n-queens/
 */
public class LeetCode51 {

    public static void main(String[] args) {
        List<List<String>> lists = new Solution().solveNQueens(4);

        System.out.println(lists);
    }

    static class Solution {

        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>(n);

            char[][] board = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = '.';
                }
            }

            backtrack(res, board, 0);
            return res;
        }

        void backtrack(List<List<String>> res, char[][] board, int row) {
            if (row == board.length) {
                res.add(boardToList(board));
                return;
            }

            int n = board[row].length;
            for (int i = 0; i < n; i++) {
                if (!isValid(board, row, i, n)) {
                    continue;
                }
                //select
                board[row][i] = 'Q';
                backtrack(res, board, row + 1);
                //unselect
                board[row][i] = '.';
            }
        }

        private List<String> boardToList(char[][] board) {
            List<String> re = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                re.add(new String(board[i]));
            }
            return re;
        }

        boolean isValid(char[][] board, int row, int col, int n) {
            //for same col
            for (int i = 0; i < row; i++) {
                char c = board[i][col];
                if (c == 'Q') {
                    return false;
                }
            }

            for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
                if (board[i][j] == 'Q') {
                    return false;
                }
            }

            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] == 'Q') {
                    return false;
                }
            }

            return true;
        }

    }


}

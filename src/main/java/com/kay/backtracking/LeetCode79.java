package com.kay.backtracking;

import com.kay.Assert;

public class LeetCode79 {

    //[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
    //"ABCCED"
    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        final boolean result = new Solution().exist(board, "ABCCED");

        Assert.isTrue(result);
    }

    static class Solution {

        static boolean [][] visited;

        public boolean exist(char[][] board, String word) {
            if (word == null || word.length() == 0) {
                return false;
            }
            visited = new boolean[board.length][board[0].length];

            char first = word.charAt(0);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == first && backtrack(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        boolean backtrack(char[][] board, String word, int row, int col, int index) {
            if (index == word.length()) {
                return true;
            }

            if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) {
                return false;
            }

            if (board[row][col] != word.charAt(index)) {
                return false;
            }

            visited[row][col] = true;
            if (backtrack(board, word, row + 1, col, index + 1) ||
                    backtrack(board, word, row - 1, col, index + 1) ||
                    backtrack(board, word, row, col + 1, index + 1) ||
                    backtrack(board, word, row, col - 1, index + 1)) {
                return true;
            }
            visited[row][col] = false;
            return false;
        }
    }
}

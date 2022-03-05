package com.kay.backtracking;

import com.kay.Assert;

public class LeetCode79 {

    //[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
    //"ABCCED"
    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        final boolean result = new Solution().exist(board, "ABCCED");

        Assert.isTrue(result);
    }

    static class Solution {

        static boolean[][] visited;
        static int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public boolean exist(char[][] board, String word) {
            if (word == null || word.length() == 0) {
                return false;
            }
            visited = new boolean[board.length][board[0].length];

            char first = word.charAt(0);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == first) {
                        visited[i][j] = true;
                        if (backtrack(board, word, i, j, 1)) {
                            return true;
                        }
                        visited[i][j] = false;
                    }
                }
            }
            return false;
        }

        boolean backtrack(char[][] board, String word, int row, int col, int index) {
            if (index == word.length()) {
                return true;
            }
            for (int[] direction : directions) {
                int x = row + direction[0];
                int y = col + direction[1];
                if (!isValid(board, word, index, x, y)) {
                    continue;
                }

                visited[x][y] = true;
                if (backtrack(board, word, x, y, index + 1)) {
                    return true;
                }
                visited[x][y] = false;
            }
            return false;
        }

        boolean isValid(char[][] board, String word, int index, int x, int y) {
            if (x < 0 || x >= board.length || y < 0 || y >= board[x].length) {
                return false;
            }
            if (board[x][y] != word.charAt(index)) {
                return false;
            }
            if (visited[x][y]) {
                return false;
            }
            return true;
        }
    }
}

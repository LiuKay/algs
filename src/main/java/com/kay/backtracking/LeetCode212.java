package com.kay.backtracking;

import com.kay.Assert;
import com.kay.ds.Trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hard
 * <p>
 * Learned: Trie
 */
public class LeetCode212 {

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};

        List<String> list = new Solution2().findWords(board, words);

        Assert.isEquals(list.toString(), "[oath, eat]");

    }

    static class Solution1 {
        static int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Set<String> res = new HashSet<>();

        public List<String> findWords(char[][] board, String[] words) {

            Trie trie = new Trie();
            for (String word : words) {
                trie.insert(word.toCharArray());
            }

            int m = board.length;
            int n = board[0].length;
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dfs(board, visited, "", i, j, trie);
                }
            }

            return new ArrayList<>(res);
        }

        public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
                return;
            }
            if (visited[x][y]) {
                return;
            }

            str += board[x][y];
            if (!trie.startWith(str.toCharArray())) {
                return;
            }

            if (trie.search(str.toCharArray())) {
                res.add(str);
            }

            visited[x][y] = true;
            for (int[] direction : directions) {
                dfs(board, visited, str, x + direction[0], y + direction[1], trie);
            }
            visited[x][y] = false;
        }
    }

    static class Solution2 {
        public List<String> findWords(char[][] board, String[] words) {
            List<String> res = new ArrayList<>();
            TrieNode tree = buildTree(words);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    dfs(board, i, j, tree, res);
                }
            }
            return res;
        }

        public void dfs(char[][] board, int x, int y, TrieNode tree, List<String> res) {
            char c = board[x][y];
            int index = c - 'a';
            if (c == '#' || tree.next[index] == null) {
                return;
            }

            tree = tree.next[index];
            if (tree.word != null) {
                res.add(tree.word);
                tree.word = null;
            }

            board[x][y] = '#';
            if (x > 0) {
                dfs(board, x - 1, y, tree, res);
            }
            if (y > 0) {
                dfs(board, x, y - 1, tree, res);
            }
            if (x < board.length - 1) {
                dfs(board, x + 1, y, tree, res);
            }
            if (y < board[x].length - 1) {
                dfs(board, x, y + 1, tree, res);
            }
            board[x][y] = c;
        }

        TrieNode buildTree(String[] words) {
            TrieNode node = new TrieNode();
            for (String word : words) {
                TrieNode p = node;
                for (char c : word.toCharArray()) {
                    int index = c - 'a';
                    if (p.next[index] == null) {
                        p.next[index] = new TrieNode();
                    }
                    p = p.next[index];
                }
                p.word = word;
            }
            return node;
        }

        static class TrieNode {
            TrieNode[] next = new TrieNode[26];
            String word;
        }
    }

}

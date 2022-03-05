package com.kay.ds;

public class Trie {

    private TrieNode root = new TrieNode('/');

    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode node = new TrieNode(text[i]);
                p.children[index] = node;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    public boolean search(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; i++) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }
        return p.isEndingChar;
    }

    public boolean startWith(char[] prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length; i++) {
            int index = prefix[i] - 'a';
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }
        return true;
    }


    static class TrieNode {
        public char data;
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;

        public TrieNode(char data) {
            this.data = data;
        }
    }
}

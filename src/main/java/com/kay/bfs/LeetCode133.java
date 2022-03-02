package com.kay.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LeetCode133 {

    static class Solution {
        public Node cloneGraph(Node node) {
            if (node == null) {
                return null;
            }

            Map<Integer, Node> map = new HashMap<>();
            Queue<Node> queue = new LinkedList<>();
            queue.offer(node);

            Node cloned = new Node(node.val);
            map.put(cloned.val, cloned);

            while (!queue.isEmpty()) {
                final Node cur = queue.poll();

                for (Node neighbor : cur.neighbors) {
                    if (!map.containsKey(neighbor.val)) {
                        map.put(neighbor.val, new Node(neighbor.val));
                        queue.offer(neighbor);
                    }
                    map.get(cur.val).neighbors.add(map.get(neighbor.val));
                }
            }

            return cloned;

        }
    }

    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int val, ArrayList<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

}

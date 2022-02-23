package com.kay.bfs;

import com.kay.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * BFS
 *
 * two end BFS
 *
 * <p>
 * https://leetcode.com/problems/open-the-lock/
 */
public class LeetCode752 {

    public static void main(String[] args) {
        String[] test1 = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        final int lock = new TwoEndSolution().openLock(test1, "8888");
        Assert.isEquals(lock, -1);

        String[] test2 = {"0201", "0101", "0102", "1212", "2002"};
        final int res = new TwoEndSolution().openLock(test2, "0202");
        Assert.isEquals(res, 6);
    }

    /**
     * start -> target
     */
    static class Solution {
        public int openLock(String[] deadends, String target) {
            Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
            if (deadSet.contains("0000")) {
                return -1;
            }

            Queue<String> queue = new LinkedList<>();
            queue.offer("0000");
            deadSet.add("0000");
            int step = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String cur = queue.poll();
                    if (target.equals(cur)) {
                        return step;
                    }
                    for (int j = 0; j < 4; j++) {
                        String up = turnUp(cur, j);
                        if (!deadSet.contains(up)) {
                            queue.offer(up);
                            deadSet.add(up);
                        }
                        String down = turnDown(cur, j);
                        if (!deadSet.contains(down)) {
                            queue.offer(down);
                            deadSet.add(down);
                        }
                    }
                }
                step++;
            }
            return -1;
        }


        String turnUp(String cur, int p) {
            char[] c = cur.toCharArray();
            if (c[p] == '9') {
                c[p] = '0';
            } else {
                c[p] += 1;
            }

            return new String(c);
        }

        String turnDown(String cur, int p) {
            char[] c = cur.toCharArray();
            if (c[p] == '0') {
                c[p] = '9';
            } else {
                c[p] -= 1;
            }

            return new String(c);
        }
    }

    /**
     * start -> target,
     * target -> start
     */
    static class TwoEndSolution {
        public int openLock(String[] deadends, String target) {
            Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
            if (deadSet.contains("0000") || deadSet.contains(target)) {
                return -1;
            }

            Set<String> q1 = new HashSet<>();
            Set<String> q2 = new HashSet<>();
            Set<String> visited = new HashSet<>();

            q1.add("0000"); // start
            q2.add(target); // end

            int step = 0;
            while (!q1.isEmpty() && !q2.isEmpty()) {
                Set<String> temp = new HashSet<>();
                for (String cur : q1) {
                    if (q2.contains(cur)) {
                        return step;
                    }

                    visited.add(cur);

                    for (int i = 0; i < 4; i++) {
                        String up = up(cur, i);
                        if (!visited.contains(up) && !deadSet.contains(up)) {
                            temp.add(up);
                        }
                        String down = down(cur, i);
                        if (!visited.contains(down) && !deadSet.contains(down)) {
                            temp.add(down);
                        }
                    }
                }

                step++;
                q1 = q2; //  iterate q1 each time
                q2 = temp;
            }
            return -1;
        }

        String up(String cur, int position) {
            char[] chars = cur.toCharArray();
            char c = chars[position];
            if (c == '9') {
                chars[position] = '0';
            } else {
                chars[position] += 1;
            }
            return new String(chars);
        }

        String down(String cur, int position) {
            char[] chars = cur.toCharArray();
            char c = chars[position];
            if (c == '0') {
                chars[position] = '9';
            } else {
                chars[position] -= 1;
            }
            return new String(chars);
        }
    }
}

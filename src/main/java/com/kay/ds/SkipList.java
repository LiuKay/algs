package com.kay.ds;

import java.util.Random;

public class SkipList {

    private static final int DEFAULT_MAX_LEVEL = 10;
    private static final double DEFAULT_FRACTION = 0.5;

    private double fraction;

    private Node head;
    private static final Random random = new Random(1);

    private int height;
    private int maxLevel;

    public SkipList(int maxLevel,double fraction) {
        head = new Node(-1, maxLevel);
        height = 1;
        this.fraction = fraction;
        this.maxLevel = maxLevel;
    }

    void insert(int value) {
        insert(value, randomLevel());
    }

    // nodeLevel is for debug usage
    void insert(int value, int nodeLevel) {
        Node current = head;
        Node[] update = new Node[maxLevel];
        for (int i = height - 1; i >= 0; i--) {
            while (current.forwards[i] != null && current.forwards[i].score < value) {
                current = current.forwards[i];
            }
            //save search path
            update[i] = current;
        }

        // current is the position to insert
        current = current.forwards[0];
        if (current == null || current.score != value) {
            if (nodeLevel > height) {
                for (int i = height; i < nodeLevel; i++) {
                    update[i] = head;
                }
                height = nodeLevel;
            }

            Node newNode = new Node(value, nodeLevel);
            for (int i = 0; i < nodeLevel; i++) {
                newNode.forwards[i] = update[i].forwards[i];
                update[i].forwards[i] = newNode;
            }
        }
    }

    Node find(int value){
        int findCount = 0;

        Node p = head;
        for (int i = height - 1; i >= 0; i--) {
            findCount++;
            while (p.forwards[i] != null && p.forwards[i].score < value) {
                p = p.forwards[i];
            }
        }

        System.out.println(String.format("search [%s] for times: %s", value, findCount));

        if (p.forwards[0] != null && p.forwards[0].score == value) {
            return p.forwards[0];
        }

        return null;
    }


    void delete(int value) {
        Node[] updates = new Node[height];

        Node cur = head;
        for (int i = height - 1; i >= 0; i--) {
            while (cur.forwards[i] != null && cur.forwards[i].score < value) {
                cur = cur.forwards[i];
            }
            updates[i] = cur;
        }

        //target node
        cur = cur.forwards[0];

        if (cur == null || cur.score != value) {
            throw new RuntimeException("Not found target value :" + value);
        }

        //from bottom to top, replace the pointers until no pointer to the target in the level i.
        //if current level has no pointer to target,either level larger than current .
        for (int i = 0; i < height; i++) {
            if (updates[i].forwards[i] != null && updates[i].forwards[i].score != value) {
                break;
            }
            updates[i].forwards[i] = cur.forwards[i];
        }

        //remove empty level
        while (height > 0 && head.forwards[height - 1] == null) {
            height--;
        }
    }


    /**
     * Each element in the list is represented by a node, the level of the node is chosen randomly while insertion in the list.
     *
     * Level does not depend on the number of elements in the node
     * @return node level to insert
     */
    private int randomLevel() {
        int level = 1;
        while (random.nextDouble() < this.fraction && level < maxLevel) {
            level++;
        }
        return level;
    }

    void printList(){
        Node p = head;
        System.out.println("=====list details=====");
        for (int i = 0; i < height; i++) {
            Node node = p.forwards[i];
            StringBuilder stringBuilder = new StringBuilder();
            while (node != null) {
                stringBuilder.append(node.score).append(",");
                node = node.forwards[i];
            }

            System.out.println(String.format("level:%s : %s", i+1, stringBuilder.toString()));
        }
    }

    static class Node{
        int score;
        Node[] forwards;

        public Node(int score, int level) {
            this.score = score;
            forwards = new Node[level];
        }
    }

    public static void main(String[] args) {
        testSkipList();
    }

    private static void  testSkipList() {
        SkipList skipList = new SkipList(DEFAULT_MAX_LEVEL, DEFAULT_FRACTION);
        skipList.insert(1,1);
        skipList.insert(2,2);
        skipList.insert(3,1);
        skipList.insert(4,3);
        skipList.insert(5,2);
        skipList.insert(6,4);
        skipList.insert(7,2);
        skipList.insert(8,4);
        skipList.insert(9,2);
        skipList.insert(10,1);

        skipList.printList();
        /**
         * level:1 : 1,2,3,4,5,6,7,8,9,10,
         * level:2 : 2,4,5,6,7,8,9,
         * level:3 : 4,6,8,
         * level:4 : 6,8,
         */

        final Node node = skipList.find(7);// search 4 times
        assert node.score == 7;

//        skipList.delete(11); // Not found target value :11

        skipList.delete(6);
        skipList.printList();

        skipList.delete(8);
        skipList.printList();

        //removed 6,8, then the level has decreased
        assert skipList.height == 3;
    }
}

package com.kay;

import com.kay.tree.tool.TreeVisitor;

import java.util.Random;
import java.util.StringJoiner;

public final class Utils {

    private Utils() {}

    public static final String SEP = ",";

    public static final String NULL_NODE = "#";

    public static ListNode newList(int... arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        int i = 1;
        while (i < arr.length) {
            ListNode next = new ListNode(arr[i]);
            cur.next = next;
            cur = cur.next;
            i++;
        }
        return head;
    }

    public static void printList(ListNode head) {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        while (head != null) {
            joiner.add(String.valueOf(head.val));
            head = head.next;
        }
        System.out.println(joiner);
    }

    public static void printArray(int[] arr){
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (int i : arr) {
            joiner.add(String.valueOf(i));
        }
        System.out.println(joiner);
    }

    public static void swap(int[] nums, int from, int to) {
        int t = nums[from];
        nums[from] = nums[to];
        nums[to] = t;
    }


    public static boolean isSorted(int[] nums) {
        return isSorted(nums, 0, nums.length - 1);
    }

    public static boolean isSorted(int[] nums, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (nums[lo] < nums[lo - 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[lo], a[lo - 1])) {
                return false;
            }
        }
        return true;
    }

    public static boolean less(Comparable v, Comparable w){
        if(v ==w)
            return false;
        return v.compareTo(w) < 0;
    }

    public static int[] random(int n){
        final int bound = 1000;
        if (n > bound) {
            throw new IllegalArgumentException(String.format("n should be less than bound %s", bound));
        }
        Random random = new Random();
        //初始化数组，随机值
        int[] arr = new int[n];
        for (int i = 0; i <n ; i++) {
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }

    public static void preOrderPrint(TreeNode treeNode) {
        StringBuilder stringBuilder = new StringBuilder();
        TreeVisitor.preOrderTraverse(treeNode, node -> {
            if (node == null) {
                stringBuilder.append(NULL_NODE).append(SEP);
            }else {
                stringBuilder.append(node.val).append(SEP);
            }
        });
        System.out.println(stringBuilder);
    }

    public static boolean isSame(TreeNode node,TreeNode other) {
        if (node == null && other == null) {
            return true;
        }
        if (node == null || other == null) {
            return false;
        }

        if (node.val != other.val) {
            return false;
        }

        return isSame(node.left, other.left) && isSame(node.right, other.right);
    }
}

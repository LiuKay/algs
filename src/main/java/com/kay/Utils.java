package com.kay;

public class Utils {

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
        System.out.print("[");
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
            if (head != null) {
                System.out.print(",");
            }
        }
        System.out.print("]");
    }

    public static void printArray(int[] arr){
        for (int i : arr) {
            System.out.print(i + ",");
        }
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
}

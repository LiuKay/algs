package com.kay.sort;

import java.util.Random;

import static com.kay.Utils.isSorted;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums = random(10); //生成 10个随机数
        sort(nums);
        assert isSorted(nums);
    }

    public static void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public static void quickSort(int[] nums, int begin, int end) {
        if(end <= begin)
            return; // 这个条件很重要，表示左边或者右边已经符合条件不用再挪,如 nums = {1, 3, 2, 4, 5},第一次p=0,左边没有数据了，显然已经不用动了，直接sort右边

        int p = partition(nums, begin, end);
        quickSort(nums, begin, p - 1);
        quickSort(nums, p + 1, end);
    }

    private static int partition(int[] nums, int begin, int end) {
        int v = nums[begin]; // 切分点的值
        int i = begin; //begin 已被占用，用 ++i 开始
        int j = end + 1; // 从 --j 开始

        while (true) {
            while (nums[++i] < v) {
                if(i == end) break;
            }
            while (nums[--j] > v) {
                if (j == begin) break;
            }
            // 边界case，i 到了v右边的第一个位置, 此时要么 i,j 相遇指向同一位置，要么j指向 v左边挨着的第一个元素，i指向v右边挨着的第一个元素
            if (i >= j) break;

            //i 找到了 >= v的，j 找到了 <= v的, 将2个值交换（小于v的移到左边，大于v的移到右边）
            swap(nums, i, j);
        }

        swap(nums, begin, j);
        return j;
    }


    private static void swap(int[] nums, int from, int to) {
        int t = nums[from];
        nums[from] = nums[to];
        nums[to] = t;
    }

    private static int[] random(int n){
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

}

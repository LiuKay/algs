package com.kay;

public class BinarySearch {

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        return -1;
    }

    public static int leftBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) { // left + 1 = right
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1; // move left
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        if (left >= nums.length || nums[left] != target) {
            return -1;
        }

        return left;
    }

    public static int rightBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) { // left + 1 = right
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1; // move right
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        if (right < 0 || nums[right] != target) {
            return -1;
        }

        return right;
    }

}

package com.kay;

public class BinarySearch {

    private BinarySearch() {
        //no-op
    }

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
        while (left <= right) { // when left > right, stop (left = right+1)
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1; // move left
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        //when target > nums[nums.length-1], left stop at nums.length.
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }

        return left;
    }

    public static int rightBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) { // when left > right, stop (left = right+1)
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

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 4};
        Assert.isTrue(2 == search(nums, 2));
        Assert.isTrue(1 == leftBound(nums, 2));
        Assert.isTrue(3 == rightBound(nums, 2));

        Assert.isTrue(-1 == leftBound(nums, 0));
        Assert.isTrue(-1 == leftBound(nums, 5));

        Assert.isTrue(-1 == rightBound(nums, 0));
        Assert.isTrue(3 == rightBound(nums, 2));
    }
}

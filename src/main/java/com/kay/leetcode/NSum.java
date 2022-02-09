package com.kay.leetcode;

import com.kay.Assert;
import com.kay.ListHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NSum {

    public static void main(String[] args) {
        int[] nums = {1,3,7,5,2};

        Arrays.sort(nums);

        final List<List<Integer>> lists = nSum(nums, 3, 0, 6);

        for (List<Integer> list : lists) {
            Assert.isTrue(list.size() == 3);
            Assert.isEquals(list.stream().reduce((x, y) -> x + y).get(), 6);
        }
    }

    static List<List<Integer>> nSum(int[] nums, int n, int start, int target) {
        int size = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n < 2 || size < n) {
            return res;
        } else if (n == 2) {
            //two sum
            int lo = start;
            int hi = size - 1;
            while (lo < hi) {
                int left = nums[lo];
                int right = nums[hi];
                int sum = left + right;
                if (sum < target) {
                    while (lo < hi && nums[lo] == left) {
                        lo++;
                    }
                } else if (sum > target) {
                    while (lo < hi && nums[hi] == right) {
                        hi--;
                    }
                }else {
                    res.add(ListHelper.asMutableList(left, right));

                    while (lo < hi && nums[lo] == left) {
                        lo++;
                    }
                    while (lo < hi && nums[hi] == right) {
                        hi--;
                    }
                }
            }
        }else {
            for (int i = 0; i < size; i++) {
                final int cur = nums[i];
                //n-1 sum
                final List<List<Integer>> lists = nSum(nums, n - 1, i + 1, target - cur);
                for (List<Integer> list : lists) {
                    // n sum
                    list.add(cur);
                    res.add(list);
                }
                while (i < size - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return res;
    }


    static List<List<Integer>> threeSum(int[] nums, int target){
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            final List<List<Integer>> lists = twoSum(nums, i + 1, target - nums[i]);
            for (List<Integer> list : lists) {
                list.add(nums[i]);
                res.add(list);
            }

            //in case of duplicate number
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return res;
    }

    //tow sum
    //int[] nums,  target
    static List<List<Integer>> twoSum(int[] nums, int start, int target) {
        Arrays.sort(nums); //O(n log(n))

        List<List<Integer>> res = new ArrayList<>();
        int lo = start;
        int hi = nums.length - 1;
        while (lo < hi) {
            int left = nums[lo];
            int right = nums[hi];
            int sum = left + right;
            if (sum < target) {
                while (lo < hi && nums[lo] == left) {
                    lo++;
                }
            } else if (sum > target) {
                while (lo < hi && nums[hi] == right) {
                    hi--;
                }
            } else {
                res.add(List.of(left, right));

                while (lo < hi && nums[lo] == left) {
                    lo++;
                }
                while (lo < hi && nums[hi] == right) {
                    hi--;
                }
            }
        }
        return res;
    }

}

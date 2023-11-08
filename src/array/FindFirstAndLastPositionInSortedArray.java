package array;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class FindFirstAndLastPositionInSortedArray {
    public static void main(String[] args) {
        int[] range = new Solution().searchRange(new int[]{1, 2, 3, 3, 3, 3, 4, 5, 9}, 3);
        System.out.println("range = " + Arrays.toString(range));
    }

    static class Solution {
        // 两次二分查找，分开查找第一个和最后一个
        // 时间复杂度 O(log n), 空间复杂度 O(1)
        // [1,2,3,3,3,3,4,5,9],target=3
        public int[] searchRange(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            int first = -1;
            int last = -1;

            // 找第一个等于target的位置
            while (left <= right) {
                int middle = (left + right) / 2;
                if (nums[middle] == target) {
                    first = middle;
                    right = middle - 1; //重点
                } else if (nums[middle] > target) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }

            // 最后一个等于target的位置
            left = 0;
            right = nums.length - 1;
            while (left <= right) {
                int middle = (left + right) / 2;
                if (nums[middle] == target) {
                    last = middle;
                    left = middle + 1; //重点
                } else if (nums[middle] > target) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }

            return new int[]{first, last};
        }
    }
}

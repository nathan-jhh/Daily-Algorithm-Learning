package array;

import java.util.Random;

/**
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
 */
public class KthLargestElement {
    static class Solution {
        private final static Random random = new Random(System.currentTimeMillis());

        // 时间复杂度：O(n), 空间复杂度O(1)
        public int findKthLargest(int[] nums, int k) {
            // 第 1 大的数，下标是 len - 1;
            // 第 2 大的数，下标是 len - 2;
            // ...
            // 第 k 大的数，下标是 len - k;
            int len = nums.length;
            int target = len - k;

            int left = 0;
            int right = len - 1;

            while (true) {
                int pivotIndex = partition(nums, left, right);
                if (pivotIndex == target) {
                    return nums[pivotIndex];
                } else if (pivotIndex < target) {
                    left = pivotIndex + 1;
                } else {
                    // pivotIndex > target
                    right = pivotIndex - 1;
                }
            }
        }

        // 快速排序双路快排思想
        private int partition(int[] nums, int left, int right) {
            int randomIndex = left + random.nextInt(right - left + 1);
            swap(nums, left, randomIndex);


            // all in nums[left + 1..le) <= pivot;
            // all in nums(ge..right] >= pivot;
            int pivot = nums[left];
            int le = left + 1;
            int ge = right;

            while (true) {
                while (le <= ge && nums[le] < pivot) {
                    le++;
                }

                while (le <= ge && nums[ge] > pivot) {
                    ge--;
                }

                if (le >= ge) {
                    break;
                }
                swap(nums, le, ge);
                le++;
                ge--;
            }

            swap(nums, left, ge);
            return ge;
        }

        private void swap(int[] nums, int index1, int index2) {
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }
    }
}

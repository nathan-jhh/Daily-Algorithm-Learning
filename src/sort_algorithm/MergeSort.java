package sort_algorithm;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/sort-an-array/submissions/
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 1, 8, 4, 9, 6, 3, 7};
        new Solution().sortArray(a);
        System.out.println("a = " + Arrays.toString(a));
    }

    static class Solution {
        //归并排序
        public int[] sortArray(int[] nums) {
            // 创建全局临时数组
            int[] temp = new int[nums.length];
            mergeSortUpToDown(nums, 0, nums.length - 1, temp);
            return nums;
        }

        // 自顶向下的归并排序
        // 对nums[low...high]归并排序
        private void mergeSortUpToDown(int[] nums, int low, int high, int[] temp) {
            // 当数组只有一个元素时停止程序
            if (low >= high) {
                return;
            }
            int mid = low + (high - low) / 2;
            mergeSortUpToDown(nums, low, mid, temp);
            mergeSortUpToDown(nums, mid + 1, high, temp);

            // 归并排序优化点：如果两个待归并的数组已经有序，那么返回
            if (nums[mid] <= nums[mid + 1]) {
                return;
            }

            // 合并两个有序区间
            //merge(nums, low, mid, high, temp);
            merge2(nums, low, mid, high, temp);
        }

        //将nums[low...mid]与nums[mid+1...high]合并,假装两部分已经是有序的。
        private void merge(int[] nums, int low, int mid, int high, int[] temp) {
            int i = low;
            int j = mid + 1;
            int k = 0;

            while (i <= mid && j <= high) {
                // 此处应是<=，而不是<，否则会破坏归并算法的稳定性。
                if (nums[i] <= nums[j]) {
                    temp[k] = nums[i];
                    i++;
                } else {
                    temp[k] = nums[j];
                    j++;
                }
                k++;
            }

            while (j <= high) {
                temp[k] = nums[j];
                j++;
                k++;
            }
            while (i <= mid) {
                temp[k] = nums[i];
                i++;
                k++;
            }

            for (int t = 0; t < k; t++) {
                nums[t + low] = temp[t];
            }
        }

        // 将nums[low...mid]与nums[mid+1...high]合并,假装两部分已经是有序的。
        // 先将low...high元素复制到temp数组相应下标中，再归并到原数组，这种方法可以避免指针偏移
        private void merge2(int[] nums, int low, int mid, int high, int[] temp) {
            for (int i = low; i <= high; i++) {
                temp[i] = nums[i];
            }
            int i = low;
            int j = mid + 1;
            for (int k = low; k <= high; k++) {
                // 若i指针越过mid，说明low...mid区间已走完，那么还需要将mid+1...high区间内剩余元素写入到原数组
                if (i == mid + 1) {
                    nums[k] = temp[j];
                    j++;
                } else if (j == high + 1) {
                    nums[k] = temp[i];
                    i++;
                } else if (temp[i] <= temp[j]) {
                    nums[k] = temp[i];
                    i++;
                } else {
                    nums[k] = temp[j];
                    j++;
                }
            }
        }
    }
}

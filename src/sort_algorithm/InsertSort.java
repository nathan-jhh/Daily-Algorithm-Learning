package sort_algorithm;

import java.util.Arrays;

import static sort_algorithm.Util.swap;

public class InsertSort {
    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 1, 8, 4, 9, 6, 3, 7};
        new Solution().sortArray(a);
        System.out.println("a = " + Arrays.toString(a));
    }

    static class Solution {
        public int[] sortArray(int[] nums) {
            insertSort(nums);
            return nums;
        }

        public int[] insertSort(int[] nums) {
            int len = nums.length;
            // 把 nums[i] 插入有序数组 nums[0..i - 1]
            for (int i = 1; i < len; i++) {
                for (int j = i; j > 0; j--) {
                    // 注意：前面的数严格大于后面的数才交换
                    if (nums[j - 1] > nums[j]) {
                        swap(nums, j, j - 1);
                    } else {
                        break;
                    }
                }
            }
            return nums;
        }
    }
}

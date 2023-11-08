package sort_algorithm;

import java.util.Arrays;

import static sort_algorithm.Util.swap;

public class SelectSort {
    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 1, 8, 4, 9, 6, 3, 7};
        new Solution().sortArray(a);
        System.out.println("a = " + Arrays.toString(a));
    }

    static class Solution {
        public int[] sortArray(int[] nums) {
            selectSort(nums);
            return nums;
        }

        //选择排序
        public int[] selectSort(int[] nums) {
            int len = nums.length;
            // 循环不变量：[0..i) 有序，且该区间里所有元素就是最终排定的样子
            // 当数组只剩最后一个元素待排序时，最后一个元素一定是最大的元素，实际已经排序完成，
            // 因此循环次数是len - 1
            for (int i = 0; i < len - 1; i++) {
                // 选择区间 [i..len - 1] 里最小的元素的索引，交换到下标 i
                // 初始时假设i位置就是最小的元素下标
                int minIndex = i;
                for (int j = i + 1; j < len; j++) {
                    if (nums[j] < nums[minIndex]) {
                        minIndex = j;
                    }
                }
                swap(nums, i, minIndex);
            }
            return nums;
        }


    }
}

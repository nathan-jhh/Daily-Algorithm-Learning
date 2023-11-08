package array;

import java.util.Arrays;

/**
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 * https://leetcode.cn/problems/sort-colors/
 */
public class SetColor {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        new Solution().sortColors(nums);
        System.out.println("nums = " + Arrays.toString(nums));
    }

    // 三路快排思想
    static class Solution {
        public void sortColors(int[] nums) {
            int len = nums.length;
            // 循环不变量：nums[0..k)中保存0元素
            // nums[k..i)中保存1元素
            // nums[j..len - 1]中保存2元素
            // i为即将要遍历的元素下标
            int i = 0;
            // 初始时保证循环不变量成立，因此[0..k)、[k..i)、[j..len - 1]需要是空区间
            int k = 0;
            int j = len;

            // 循环终止的条件是i = j，当i = j时，[k..i)与[j..len - 1]重合，没有元素可以去处理
            while (i < j) {
                if (nums[i] == 0) {
                    swap(nums, i, k);
                    k++;
                    i++;
                } else if (nums[i] == 1) {
                    i++;
                } else if (nums[i] == 2) {
                    j--;
                    swap(nums, i, j);
                }
            }
        }

        //交换元素
        private void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}

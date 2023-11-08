package array;

/**
 * 26. 删除有序数组中的重复项
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，
 * 使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        int num = new Solution2().removeDuplicates(new int[]{1, 2, 2, 2, 3, 3});
        System.out.println("num = " + num);
    }

    static class Solution2 {
        public int removeDuplicates(int[] nums) {
            if (nums.length == 1) {
                return 1;
            }

            // 循环不变量：nums[0..j] 没有重复元素
            int j = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[j]) {
                    j++;
                    nums[j] = nums[i];
                }
            }
            return j + 1;
        }
    }

    static class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums.length == 1) {
                return 1;
            }

            int slow = 0;
            int fast = 1;
            while (fast <= nums.length - 1) {
                if (nums[fast] != nums[slow]) {
                    slow++;
                    nums[slow] = nums[fast];
                }
                fast++;
            }
            return slow + 1;
        }
    }
}

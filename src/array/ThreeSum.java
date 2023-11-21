package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。
 * 请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * https://leetcode.cn/problems/3sum/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums2 = {-1, -1, 2, -1, 2};
        List<List<Integer>> lists = new Solution().threeSum(nums2);
        System.out.println("lists = " + lists);
    }

    static class Solution {
        // 双指针
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            // 先排序
            Arrays.sort(nums);

            for (int i = 0; i <= nums.length - 3; i++) {
                // 如果数组中最小的元素都大于0，那后边的元素更不可能等于0了
                if (nums[i] > 0) {
                    return result;
                }

                // 去重
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum > 0) {
                        right--;
                    } else if (sum < 0) {
                        left++;
                    } else { // sum = 0
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        // 此时，要记得数组已经排好序了
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }

                        right--;
                        left++;
                    }
                }
            }
            return result;
        }
    }
}

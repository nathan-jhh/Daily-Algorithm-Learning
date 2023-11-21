package dynamicprogramming;

/**
 * 53. 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 * https://leetcode.cn/problems/maximum-subarray/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class MaxSubArray {
    class Solution {
        public int maxSubArray(int[] nums) {
            // 1. 定义状态：dp[i]：表示以 nums[i] 结尾 的 连续 子数组的最大和。

            // 2. 状态转移方程：根据状态的定义，由于 nums[i] 一定会被选取，
            // 并且以 nums[i] 结尾的连续子数组与以 nums[i - 1] 结尾的连续子数组只相差一个元素 nums[i] 。
            // dp[i - 1] 有可能是负数，若dp[i - 1] > 0，则dp[i] = dp[i - 1] + nums[i]；
            // 若dp[i - 1] <= 0，dp[i] = nums[i]

            // 3. 计算初始值
            // dp[0] = nums[0]

            // 注意：这里状态的定义不是题目中的问题的定义，不能直接将最后一个状态返回回去;
            // 原问题的输出是：把所有的 dp[0]、dp[1]、……、dp[n - 1] 都看一遍，取最大值。

            int len = nums.length;
            // dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
            int[] dp = new int[len];
            dp[0] = nums[0];

            for (int i = 1; i < len; i++) {
                if (dp[i - 1] > 0) {
                    dp[i] = dp[i - 1] + nums[i];
                } else {
                    dp[i] = nums[i];
                }
            }

            // 也可以在上面遍历的同时求出 res 的最大值
            int res = dp[0];
            for (int i = 1; i < len; i++) {
                res = Math.max(res, dp[i]);
            }
            return res;
        }
    }
}

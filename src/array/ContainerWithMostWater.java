package array;

/**
 * 11. 盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * https://leetcode.cn/problems/container-with-most-water/description/
 */
public class ContainerWithMostWater {
    class Solution {
        public int maxArea(int[] height) {
            int left = 0, right = height.length - 1;
            int res = 0;

            while (left < right) {
                // [left, right] 之间的矩形面积
                int cur_area = Math.min(height[left], height[right]) * (right - left);
                res = Math.max(res, cur_area);
                // 双指针
                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }
            return res;
        }
    }
}

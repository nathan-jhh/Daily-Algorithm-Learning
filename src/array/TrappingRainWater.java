package array;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * https://leetcode.cn/problems/trapping-rain-water/
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        int trap = new Solution().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        System.out.println("trap = " + trap);
    }

    // 时间复杂度O(n),空间复杂度O(1)
    static class Solution {
        public int trap(int[] height) {
            int len = height.length;

            int left = 0;
            int right = len - 1;
            // l_max 是 height[0..left] 中最高柱子的高度，
            // r_max 是 height[right..end] 的最高柱子的高度。
            int l_max = 0, r_max = 0;
            int res = 0;

            while (left < right) {
                l_max = Math.max(l_max, height[left]);
                r_max = Math.max(r_max, height[right]);

                // res += min(l_max, r_max) - height[i]
                if (l_max < r_max) {
                    res += l_max - height[left];

                    left++;
                } else {
                    res += r_max - height[right];
                    right--;
                }
            }
            return res;
        }
    }

    // 时间复杂度O(n),空间复杂度O(n)
    static class Solution2 {
        public int trap(int[] height) {
            int len = height.length;

            // 表示i位置左边的最高的柱子高度
            int[] lMax = new int[len];
            // 表示i位置右边的最高的柱子高度
            int[] rMax = new int[len];
            lMax[0] = height[0];
            rMax[len - 1] = height[len - 1];

            // 计算第i位置左边的最高的柱子高度，只需要和i-1位置左边最高柱子高度比较就行了
            for (int i = 1; i < len; i++) {
                lMax[i] = Math.max(lMax[i - 1], height[i]);
            }
            for (int i = len - 2; i >= 0; i--) {
                rMax[i] = Math.max(rMax[i + 1], height[i]);
            }

            int res = 0;
            for (int i = 1; i < len - 1; i++) {
                res += Math.min(rMax[i], lMax[i]) - height[i];
            }

            return res;
        }
    }

    // 时间复杂度O(n^2),空间复杂度O(1)
    static class Solution3 {
        // 对于位置i，能够装的水 = min(i左边最高的柱子高度，i右边最高的柱子高度) - height[i]
        public int trap(int[] height) {
            int water = 0;
            for (int i = 0; i < height.length; i++) {
                int lMaxHeight = 0;
                for (int j = 0; j < i; j++) {
                    if (height[j] > lMaxHeight) {
                        lMaxHeight = height[j];
                    }
                }

                int rMaxHeight = 0;
                for (int j = i + 1; j < height.length; j++) {
                    if (height[j] > rMaxHeight) {
                        rMaxHeight = height[j];
                    }
                }

                // 如果i位置高度高于min(i左边最高的柱子高度，i右边最高的柱子高度)，则不能盛水
                if (height[i] < Math.min(rMaxHeight, lMaxHeight)) {
                    water += Math.min(rMaxHeight, lMaxHeight) - height[i];
                }
            }
            return water;
        }
    }
}

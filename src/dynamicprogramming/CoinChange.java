package dynamicprogramming;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 * https://leetcode.cn/problems/coin-change/description/
 */
public class CoinChange {
    public static void main(String[] args) {
        int num = new Solution2().coinChange(new int[]{1, 2, 5}, 11);
        System.out.println("num = " + num);
    }

    static class Solution {
        // 输入一个总金额，返回可以凑成总金额所需的最少的硬币个数
        public int coinChange(int[] coins, int amount) {
            if (amount == 0) {
                return 0;
            }
            // (amount - coin) 可能小于0
            if (amount < 0) {
                return -1;
            }

            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                int subProblem = coinChange(coins, amount - coin);
                // 子问题无解则跳过
                if (subProblem == -1) {
                    continue;
                }
                res = Math.min(res, subProblem + 1);
            }

            return res == Integer.MAX_VALUE ? -1 : res;
        }
    }

    // 记忆化搜索，使用备忘录记录重叠子问题
    static class Solution2 {
        int[] memo;

        public int coinChange(int[] coins, int amount) {
            memo = new int[amount + 1];
            // 备忘录初始化为一个不会被取到的特殊值，代表还未被计算
            Arrays.fill(memo, -666);

            return dp(coins, amount);
        }

        // 输入一个总金额，返回可以凑成总金额所需的最少的硬币个数
        private int dp(int[] coins, int amount) {
            if (amount == 0) {
                return 0;
            }
            // (amount - coin) 可能小于0
            if (amount < 0) {
                return -1;
            }

            // 查备忘录，防止重复计算
            if (memo[amount] != -666) {
                return memo[amount];
            }

            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                int subProblem = dp(coins, amount - coin);
                // 子问题无解则跳过
                if (subProblem == -1) {
                    continue;
                }
                res = Math.min(res, subProblem + 1);
            }

            // 把计算结果存入备忘录
            memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;

            return memo[amount];
        }
    }
}
